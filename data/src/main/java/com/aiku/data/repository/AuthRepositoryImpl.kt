package com.aiku.data.repository

import com.aiku.data.source.local.AuthLocalDataSource
import com.aiku.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(private val authLocalDataSource: AuthLocalDataSource) : AuthRepository{

    override suspend fun saveAuthToken(token: String) {
        authLocalDataSource.saveAuthToken(token)
    }

    override fun getAuthToken(): Flow<String?> {
        return authLocalDataSource.getAuthToken()
    }

    override suspend fun clearAuthToken() {
        authLocalDataSource.clearAuthToken()
    }
}
