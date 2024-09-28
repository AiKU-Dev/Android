package com.aiku.domain.repository

import com.aiku.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun loginWithKakaoTalk(): Flow<Token>
    fun loginWithKakaoAccount(): Flow<Token>
    fun loginWithToken(refreshToken: String): Flow<Token>
}