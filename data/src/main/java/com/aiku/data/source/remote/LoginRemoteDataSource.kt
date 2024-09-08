package com.aiku.data.source.remote

import android.content.Context
import com.aiku.data.model.remote.BaseResponse
import com.aiku.data.model.remote.LoginResponse
import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.UNKNOWN
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

class LoginRemoteDataSource @Inject constructor(
    @ActivityContext private val context: Context
) {

    suspend fun loginWithKakaoTalk(): LoginResponse {
        return withContext(Dispatchers.IO) {
            try {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                    performLogin { callback ->
                        UserApiClient.instance.loginWithKakaoTalk(
                            context,
                            callback = callback
                        )
                    }
                } else {
                    loginWithKakaoAccount()
                }
            } catch (e: Exception) {
                LoginResponse(null, ErrorResponse(UNKNOWN, "An unknown error occurred"))
            }
        }
    }

    suspend fun loginWithKakaoAccount(): LoginResponse {
        return withContext(Dispatchers.IO) {
            try {
                performLogin { callback ->
                    UserApiClient.instance.loginWithKakaoAccount(
                        context,
                        callback = callback
                    )
                }
            } catch (e: Exception) {
                LoginResponse(null, ErrorResponse(UNKNOWN, "An unknown error occurred"))
            }
        }
    }

    private suspend fun performLogin(
        loginAction: (callback: (token: OAuthToken?, error: Throwable?) -> Unit) -> Unit
    ): LoginResponse {
        return suspendCancellableCoroutine { continuation ->
            loginAction { token, error ->
                if (error != null) {
                    continuation.resume(
                        LoginResponse(
                            null,
                            ErrorResponse(UNKNOWN, "An unknown error occurred")
                        )
                    )
                } else if (token != null) {
                    continuation.resume(LoginResponse(token, null)) //임시

//                      idToken 서버로 전송 (token.idToken)
//                      성공 : continuation.resume(LoginResponse(token, null))
//                      실패 : 회원정보 없음, idToken 양식이상
//                    val error = when (serverResponse.errorCode) {
//                        USER_NOT_FOUND -> ErrorResponse(USER_NOT_FOUND, "User not found")
//                        INVALID_ID_TOKEN -> ErrorResponse(INVALID_ID_TOKEN, "ID token is invalid")
//                        else -> ErrorResponse(UNKNOWN, "Unknown error from server")
//                    }
//                    continuation.resume(LoginResponse(null, error))

                } else {
                    continuation.resume(
                        LoginResponse(
                            null,
                            ErrorResponse(UNKNOWN, "An unknown error occurred")
                        )
                    )
                }
            }

            continuation.invokeOnCancellation {
                // 로그인 요청 취소 처리
            }
        }
    }

    fun loginWithToken(refreshToken: String) : BaseResponse<String> {
        // TODO : refreshToken 서버로 전송 후, accessToken 재발급
        //  성공 : 발급받은 accessToken 저장 후, 로그인 성공
        //  실패 : refreshToken 양식 이상, 만료된 refreshToken
        return BaseResponse(true, null, "재발급된 accessToken")
    }
}

