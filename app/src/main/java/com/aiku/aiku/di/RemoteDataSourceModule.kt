package com.aiku.aiku.di

import com.aiku.data.api.remote.GroupApi
import com.aiku.data.source.remote.GroupRemoteDataSource
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
    fun provideUserRemoteDataSource(): UserRemoteDataSource {
        return UserRemoteDataSource()
    }

    @Provides
    @Singleton
    fun provideGroupRemoteDataSource(
        groupApi: GroupApi
    ): GroupRemoteDataSource {
        return GroupRemoteDataSource(groupApi)
    }
}