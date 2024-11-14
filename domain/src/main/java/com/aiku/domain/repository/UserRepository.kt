package com.aiku.domain.repository

import com.aiku.domain.model.user.NewUser
import com.aiku.domain.model.user.Terms
import com.aiku.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUser(user: NewUser): Result<Unit>
    fun fetchUser(): Flow<User>

    suspend fun fetchTerms(): Terms
    suspend fun fetchIsNicknameExist(nickname: String): Result<Unit>
}