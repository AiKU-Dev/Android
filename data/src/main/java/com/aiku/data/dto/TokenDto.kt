package com.aiku.data.dto

import com.aiku.domain.model.Token
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenDto(
    @Json(name = "grantType") val grantType: String?,
    @Json(name = "accessToken") val accessToken: String?,
    @Json(name = "refreshToken") val refreshToken: String?,
    @Json(name = "memberId") val memberId: Int?
){
    fun toToken(): Token = Token(
        grantType = grantType ?: "",
        accessToken = accessToken ?: "",
        refreshToken = refreshToken ?: "",
        memberId = memberId ?: 0
    )
}
