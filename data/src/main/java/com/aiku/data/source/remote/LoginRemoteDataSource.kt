package com.aiku.data.source.remote

import android.content.Context
import com.aiku.data.api.remote.AuthApi
import com.aiku.data.dto.TokenDto
import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.UNKNOWN
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class LoginRemoteDataSource @Inject constructor(
    @ActivityContext private val context: Context,
    private val authApi: AuthApi
) {

    suspend fun loginWithKakaoTalk() : TokenDto {
        return withContext(Dispatchers.IO) {
            try {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                    performLogin { callback ->
                        UserApiClient.instance.loginWithKakaoTalk(context, callback = callback) }
                } else {
                    loginWithKakaoAccount()
                }
            } catch (e: Exception) {
                throw ErrorResponse(UNKNOWN, "An unknown error occurred")
            }
        }
    }

    suspend fun loginWithKakaoAccount() : TokenDto {
        return withContext(Dispatchers.IO) {
            try {
                performLogin { callback ->
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback) }
            } catch (e: Exception) {
                throw ErrorResponse(UNKNOWN, "An unknown error occurred")
            }
        }
    }

    private suspend fun performLogin(
        loginAction: (callback: (token: OAuthToken?, error: Throwable?) -> Unit) -> Unit
    ): TokenDto {
        return try {
            // Kakao 로그인
            val tokenResult = suspendCoroutine<OAuthToken?> { continuation ->
                loginAction { token, error ->
                    if (error != null) {
                        continuation.resumeWith(Result.failure(error))
                    } else {
                        continuation.resumeWith(Result.success(token))
                    }
                }
            }
            // idToken -> AT, RT 발급
            val idToken = tokenResult?.idToken ?: throw ErrorResponse(UNKNOWN, "No ID Token provided")

            // 임시로 쓰레기값을 반환
            val fakeTokenDto = TokenDto(
                grantType = "Bearer",
                accessToken = "fakeAccessToken1234",
                refreshToken = "fakeRefreshToken1234",
                memberId = 99999
            )
            fakeTokenDto
            // TODO : authApi.issueToken(idToken)로 추후에 수정

        } catch (e: Exception) {
            throw ErrorResponse(UNKNOWN, "An error occurred: ${e.message}")
        }
    }

    //RT -> AT 재발급
    suspend fun loginWithToken(refreshToken: String): TokenDto {
        return withContext(Dispatchers.IO) {
            try {
                // 임시로 쓰레기값을 반환
                val fakeTokenDto = TokenDto(
                    grantType = "Bearer",
                    accessToken = "fakeAccessToken1234",
                    refreshToken = "fakeRefreshToken1234",
                    memberId = 99999
                )
                fakeTokenDto
                // TODO : authApi.issueAccessToken(refreshToken)
            } catch (e: Exception) {
                throw ErrorResponse(UNKNOWN, "Failed to refresh token: ${e.message}")
            }
        }
    }
}

