package com.aiku.aiku.di

import android.content.Context
import com.aiku.data.api.local.UserDataStoreManager
import com.aiku.data.source.local.TermsLocalDataSource
import com.aiku.data.source.local.UserLocalDataSource
import com.aiku.data.source.remote.LoginRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
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

    @Provides
    @Singleton
    fun provideTermsLocalDataSource(@ApplicationContext context: Context): TermsLocalDataSource {
        return TermsLocalDataSource(context)
    }
}
