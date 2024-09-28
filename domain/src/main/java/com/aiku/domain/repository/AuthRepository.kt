package com.aiku.domain.repository

import com.aiku.domain.model.Token

interface AuthRepository {
    suspend fun saveAccessToken(token: Token)
    suspend fun saveRefreshToken(token: Token)
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun removeAccessToken()
    suspend fun removeRefreshToken()
}