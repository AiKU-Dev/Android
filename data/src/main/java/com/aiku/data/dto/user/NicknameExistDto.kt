package com.aiku.data.dto.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NicknameExistDto(
    @Json(name = "exist") val exist: Boolean
)