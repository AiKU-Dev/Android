package com.aiku.data.source.remote

import com.aiku.data.dto.UserDto
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    // private val userApi: UserApi
) {

    suspend fun saveUser(user: UserDto) {
        // userApi.saveUser(user)
    }

    suspend fun getUser(): UserDto {
        // return userApi.getUser()
        TODO()
    }
}