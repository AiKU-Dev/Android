package com.aiku.data.source.local

import com.aiku.data.UserEntity
import com.aiku.data.api.local.UserDataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val dataStoreManager: UserDataStoreManager
) {

    suspend fun saveUser(user: UserEntity) {
        dataStoreManager.saveUser(user)
    }

    fun getUser(): Flow<UserEntity> {
        return dataStoreManager.getUser()
    }
}
