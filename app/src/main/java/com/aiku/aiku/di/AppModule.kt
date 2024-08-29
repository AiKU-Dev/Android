package com.aiku.aiku.di

import android.content.Context
import com.aiku.data.repository.LoginRepositoryImpl
import com.aiku.data.source.remote.LoginRemoteDataSource
import com.aiku.domain.delegate.UserDelegate
import com.aiku.domain.repository.LoginRepository
import com.aiku.data.delegate.UserDelegateImpl
import com.aiku.data.source.remote.LoginRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoginRemoteDataSource(
        @ApplicationContext context: Context
    ): LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(context)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(loginRemoteDataSource: LoginRemoteDataSource): LoginRepository {
        return LoginRepositoryImpl(loginRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideUserDelegate(): UserDelegate {
        return UserDelegateImpl()
    }
}
