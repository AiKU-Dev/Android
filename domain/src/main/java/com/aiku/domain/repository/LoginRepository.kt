package com.aiku.domain.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun loginWithKakaoTalk(): Flow<String?>
    suspend fun loginWithKakaoAccount(): Flow<String?>
}