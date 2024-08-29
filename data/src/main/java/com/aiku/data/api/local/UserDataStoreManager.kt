package com.aiku.data.api.local

import androidx.datastore.core.DataStore
import com.aiku.data.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataStoreManager @Inject constructor(
    private val userDataStore: DataStore<UserEntity>
) {

    suspend fun saveUser(user: UserEntity) {
        userDataStore.updateData {
            it.toBuilder()
                .setImage(user.image ?: "")
                .setName(user.name ?: "")
                .setPhoneNumber(user.phoneNumber ?: "")
                .build()
        }
    }

    fun getUser(): Flow<UserEntity> {
        return userDataStore.data
    }
}
