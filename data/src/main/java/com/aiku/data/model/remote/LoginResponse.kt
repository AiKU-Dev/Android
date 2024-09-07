package com.aiku.data.model.remote

import com.aiku.domain.exception.ErrorResponse
import com.kakao.sdk.auth.model.OAuthToken

data class LoginResponse(
    val token: OAuthToken?,
    val error: ErrorResponse?
)