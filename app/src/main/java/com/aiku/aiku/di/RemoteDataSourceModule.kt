package com.aiku.aiku.di

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
}