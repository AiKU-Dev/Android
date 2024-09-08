package com.aiku.aiku.di

import android.content.Context
import com.aiku.data.repository.LoginRepositoryImpl
import com.aiku.data.source.remote.LoginRemoteDataSource
import com.aiku.domain.repository.AuthRepository
import com.aiku.domain.repository.LoginRepository
import com.aiku.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    @ActivityScoped
    fun provideLoginUseCase(loginRepository: LoginRepository, authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(loginRepository = loginRepository, authRepository = authRepository)
    }

    @Provides
    @ActivityScoped
    fun provideLoginRepository(
        loginRemoteDataSource: LoginRemoteDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(loginRemoteDataSource)
    }

    @Provides
    @ActivityScoped
    fun provideLoginRemoteDataSource(@ActivityContext context: Context): LoginRemoteDataSource {
        return LoginRemoteDataSource(context)
    }
}