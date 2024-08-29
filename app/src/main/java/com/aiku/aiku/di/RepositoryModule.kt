package com.aiku.aiku.di

import com.aiku.aiku.qualifer.IoDispatcher
import com.aiku.data.repository.UserRepositoryImpl
import com.aiku.data.source.local.UserLocalDataSource
import com.aiku.data.source.remote.UserRemoteDataSource
import com.aiku.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        userLocalDataSource: UserLocalDataSource,
        userRemoteDataSource: UserRemoteDataSource,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): UserRepository {
        return UserRepositoryImpl(userLocalDataSource, userRemoteDataSource, coroutineDispatcher)
    }
}