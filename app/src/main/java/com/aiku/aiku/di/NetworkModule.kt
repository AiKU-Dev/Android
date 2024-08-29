package com.aiku.aiku.di

import com.aiku.aiku.BuildConfig
import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.UNKNOWN
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String,
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
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        httpExceptionInterceptor: (chain: Interceptor.Chain) -> Response
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(httpExceptionInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpExceptionInterceptor(
        moshi: Moshi
    ) : (chain: Interceptor. Chain) -> Response {
        return { chain ->
            val response = chain.proceed(chain.request())
            if (response.isSuccessful.not()) {
                val errorBody = response.body?.string().also {
                    if (it == null) {
                        throw ErrorResponse(message = "Unknown error", code = UNKNOWN)
                    }
                }
                val exception = moshi.adapter(ErrorResponse::class.java).fromJson(errorBody!!)
                throw exception ?: ErrorResponse(message = "Unknown error", code = UNKNOWN)
            }
            response
        }
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
            .build()
    }
}