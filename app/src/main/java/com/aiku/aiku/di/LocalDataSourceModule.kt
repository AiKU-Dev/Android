package com.aiku.aiku.di

import com.aiku.data.api.local.UserDataStoreManager
import com.aiku.data.source.local.UserLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideUserLocalDataSource(
        userDataStoreManager: UserDataStoreManager
    ): UserLocalDataSource {
        return UserLocalDataSource(userDataStoreManager)
    }
}