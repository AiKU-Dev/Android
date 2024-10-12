package com.aiku.aiku.di

import com.aiku.data.api.remote.GroupApi
import com.aiku.data.api.remote.UserApi
import com.aiku.data.source.remote.GroupRemoteDataSource
import android.content.Context
import com.aiku.core.qualifer.IoDispatcher
import com.aiku.data.api.remote.CreateScheduleApi
import com.aiku.data.source.remote.CreateScheduleRemoteDataSource
import com.aiku.data.source.remote.LoginRemoteDataSource
import com.aiku.data.source.remote.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(
        userApi: UserApi
    ): UserRemoteDataSource {
        return UserRemoteDataSource(userApi)
    }

    @Provides
    @Singleton
    fun provideGroupRemoteDataSource(
        groupApi: GroupApi
    ): GroupRemoteDataSource {
        return GroupRemoteDataSource(groupApi)
    }

    @Provides
    @Singleton
    fun provideCreateScheduleRemoteDataSource(
        @ApplicationContext context: Context,
        createScheduleApi: CreateScheduleApi,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): CreateScheduleRemoteDataSource {
        return CreateScheduleRemoteDataSource(context,createScheduleApi, coroutineDispatcher)
    }
}