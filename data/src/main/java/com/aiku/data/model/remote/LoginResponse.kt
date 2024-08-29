package com.aiku.data.model.remote
import com.aiku.domain.model.response.LoginResult
import com.kakao.sdk.auth.model.OAuthToken

data class LoginResponse(
    val token: OAuthToken? = null,
    val error: Throwable? = null
)

// mapper
fun LoginResponse.toLoginResult(): LoginResult {
    return LoginResult(
        //token = this.token,
        error = this.error
    )
}