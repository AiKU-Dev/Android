package com.aiku.aiku.di

import com.aiku.data.api.remote.GroupApi
import com.aiku.data.api.remote.ScheduleApi
import com.aiku.data.api.remote.UserApi
import com.aiku.data.api.remote.UserNoAuthApi
import com.aiku.data.source.remote.GroupRemoteDataSource
import com.aiku.data.source.remote.ScheduleRemoteDataSource
import com.aiku.data.source.remote.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(
        userApi: UserApi,
        userNoAuthApi: UserNoAuthApi
    ): UserRemoteDataSource {
        return UserRemoteDataSource(userApi, userNoAuthApi)
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
    fun provideScheduleRemoteDataSource(
        scheduleApi: ScheduleApi
    ): ScheduleRemoteDataSource {
        return ScheduleRemoteDataSource(scheduleApi)
    }
}