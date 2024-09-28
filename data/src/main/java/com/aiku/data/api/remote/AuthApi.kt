package com.aiku.data.api.remote

import com.aiku.data.dto.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login/sign-in")
    suspend fun issueToken(@Body idToken: String): TokenDto

    @POST("login/refresh")
    suspend fun issueAccessToken(@Body idToken: String): TokenDto //TODO : Body 수정
}