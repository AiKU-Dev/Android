package com.aiku.domain.repository

import com.aiku.domain.model.response.LoginResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun loginWithKakaoTalk(): Flow<LoginResult>
    suspend fun loginWithKakaoAccount(): Flow<LoginResult>

}