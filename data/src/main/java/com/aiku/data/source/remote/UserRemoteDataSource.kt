package com.aiku.data.source.remote

import com.aiku.data.api.remote.UserApi
import com.aiku.data.api.remote.UserNoAuthApi
import com.aiku.data.dto.user.NewUserDto
import com.aiku.data.dto.user.NicknameExistDto
import com.aiku.data.dto.user.TermsDto
import com.aiku.data.dto.user.UserDto
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userApi: UserApi,
    private val userNoAuthApi: UserNoAuthApi
) {

    suspend fun saveUser(user: NewUserDto) {
        // userApi.saveUser(user)
    }

    suspend fun fetchUser(): UserDto {
        return userApi.fetchUser()
    }

    suspend fun fetchTerms(): TermsDto {
        return userApi.fetchTerms()
    }

    suspend fun fetchIsNicknameExist(nickname: String): NicknameExistDto {
        return userNoAuthApi.fetchIsNicknameExist(nickname)
    }
}