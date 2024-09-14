package com.aiku.aiku.di

import com.aiku.aiku.BuildConfig
import com.aiku.core.adapter.LocalDateTimeAdapter
import com.aiku.core.qualifer.AuthHeaderInterceptor
import com.aiku.core.qualifer.BaseUrl
import com.aiku.core.qualifer.ResponseExceptionInterceptor
import com.aiku.core.qualifer.ResponseParsingInterceptor
import com.aiku.data.api.remote.GroupApi
import com.aiku.data.source.local.TokenLocalDataSource
import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.UNKNOWN
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @BaseUrl
    @Provides
    @Singleton
    fun provideBaseUrl() : String {
        return "https://api.aiku.com/"   // TODO : BaseUrl
    }

    @Provides
    @Singleton
    fun provideAuthenticator(
        tokenLocalDataSource: TokenLocalDataSource,
    ): Authenticator {
        return Authenticator { _, response ->
            runBlocking {
                val refreshToken = tokenLocalDataSource.getRefreshToken()
                if (refreshToken == null) {
                    response.close()
                    return@runBlocking null
                }

                response.request.newBuilder()
                    .removeHeader("Authorization")
                    .addHeader("Authorization", "Bearer $refreshToken")
                    .build()
            }
        }
    }

    @AuthHeaderInterceptor
    @Provides
    @Singleton
    fun provideAuthInterceptor(
        tokenLocalDataSource: TokenLocalDataSource,
    ): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            runBlocking {
                val accessToken = tokenLocalDataSource.getAccessToken() ?: ""
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $accessToken")
                    .build()
                chain.proceed(newRequest)
            }
        }
    }

    @ResponseParsingInterceptor
    @Provides
    @Singleton
    fun provideResponseParsingInterceptor() : Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val body = response.body?.string() ?: throw ErrorResponse(UNKNOWN, "Unknown Error", "")

            val jsonObject = JSONObject(body)
            val resultObject = jsonObject.getJSONObject("result")
            val newResponseBody = resultObject.toString().toResponseBody(response.body?.contentType())

            response.newBuilder()
                .body(newResponseBody)
                .build()
        }
    }

    @ResponseExceptionInterceptor
    @Provides
    @Singleton
    fun provideHttpExceptionInterceptor(
        moshi: Moshi
    ) : Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            if (response.isSuccessful.not()) {
                val errorBody = response.body?.string().also {
                    if (it == null) {
                        throw ErrorResponse(UNKNOWN, "Unknown Error", "")
                    }
                }
                val exception = moshi.adapter(ErrorResponse::class.java).fromJson(errorBody!!)
                throw exception ?: ErrorResponse(UNKNOWN, "Unknown Error", "")
            }
            response
        }
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @AuthHeaderInterceptor authHeaderInterceptor: Interceptor,
        @ResponseExceptionInterceptor responseExceptionInterceptor: Interceptor,
        @ResponseParsingInterceptor responseParsingInterceptor: Interceptor,
        authenticator: Authenticator
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authHeaderInterceptor)
            .addInterceptor(responseExceptionInterceptor)
            .addInterceptor(responseParsingInterceptor)
            .authenticator(authenticator)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideMoshi() : Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(LocalDateTimeAdapter())
            .build()
    }
}