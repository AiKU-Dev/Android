package com.aiku.domain.repository

import com.aiku.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun saveUser(user: User): Flow<Unit>
    fun getUser(): Flow<User>
}