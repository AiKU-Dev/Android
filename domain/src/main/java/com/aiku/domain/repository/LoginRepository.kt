package com.aiku.domain.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun loginWithKakaoTalk(): Flow<String?>
    fun loginWithKakaoAccount(): Flow<String?>
    fun loginWithToken(refreshToken: String): Flow<String?>
}