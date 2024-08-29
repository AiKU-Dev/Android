package com.aiku.domain.model.response

import com.kakao.sdk.auth.model.OAuthToken
import com.aiku.domain.model.UserModel

data class LoginResult(
    val token: OAuthToken? = null,
    val user: UserModel? = null, // 사용자 정보 추가
    val error: Throwable? = null
)
