package com.aiku.aiku.di

import android.content.Context
import com.aiku.data.api.remote.GroupApi
import com.aiku.data.source.remote.GroupRemoteDataSource
import com.aiku.data.source.remote.LoginRemoteDataSource
import com.aiku.data.source.remote.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
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
        //groupApi: GroupApi
    ): GroupRemoteDataSource {
        return GroupRemoteDataSource()
    }
}