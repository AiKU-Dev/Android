package com.aiku.data.model.remote

import com.aiku.domain.exception.ErrorResponse
import com.kakao.sdk.auth.model.OAuthToken

data class LoginResponse(
    val token: OAuthToken?,
    val error: ErrorResponse?
)

//TODO : core로 이동
data class BaseResponse<T>(
    val success: Boolean,
    val error: ErrorResponse?,
    val data: T? = null
)