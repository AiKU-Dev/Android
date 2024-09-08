package com.aiku.aiku.di

import com.aiku.domain.repository.AuthRepository
import com.aiku.domain.repository.LoginRepository
import com.aiku.domain.repository.UserRepository
import com.aiku.domain.usecase.LoginUseCase
import com.aiku.domain.usecase.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSaveUserUseCase(
        userRepository: UserRepository
    ): SaveUserUseCase {
        return SaveUserUseCase(userRepository)
    }

}