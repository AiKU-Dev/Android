package com.aiku.domain.model


data class Token(
    val grantType: String?,
    val accessToken: String?,
    val refreshToken: String?,
    val memberId: Int?
)