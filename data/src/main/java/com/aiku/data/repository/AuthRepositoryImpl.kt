package com.aiku.data.repository

import com.aiku.data.source.local.AuthLocalDataSource
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(private val authLocalDataSource: AuthLocalDataSource) {

    suspend fun saveAuthToken(token: String) {
        authLocalDataSource.saveAuthToken(token)
    }

    fun getAuthToken(): Flow<String?> {
        return authLocalDataSource.getAuthToken()
    }

    suspend fun clearAuthToken() {
        authLocalDataSource.clearAuthToken()
    }
}
