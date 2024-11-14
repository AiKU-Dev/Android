package com.aiku.data.api.remote

import com.aiku.data.dto.user.NicknameExistDto
import com.aiku.data.dto.user.TermsDto
import com.aiku.data.dto.user.UserDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun fetchUser(): UserDto

    @GET("term")
    suspend fun fetchTerms(): TermsDto

}

interface UserNoAuthApi {

    @GET("users/nickname")
    suspend fun fetchIsNicknameExist(@Query("nickname") nickname: String): NicknameExistDto
}