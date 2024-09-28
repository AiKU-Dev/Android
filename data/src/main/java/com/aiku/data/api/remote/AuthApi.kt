package com.aiku.data.api.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login/sign-in")
    suspend fun issueToken(@Body idToken: String): Response<String> //TODO : 수정

    @POST("login/refresh")
    suspend fun issueAccessToken(@Body idToken: String): Response<String>
}