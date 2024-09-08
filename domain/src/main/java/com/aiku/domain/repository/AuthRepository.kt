package com.aiku.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    //TODO : 서버와 통신해서 Token 가져오기 -> 이후 아래 함수로 로컬에 저장/불러오기/삭제
    suspend fun saveAuthToken(token: String)
    fun getAuthToken(): Flow<String?>
    suspend fun clearAuthToken()
}
