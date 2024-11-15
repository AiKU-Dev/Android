package com.aiku.data.source.remote

import android.content.Context
import com.aiku.data.api.remote.NoAuthTokenApi
import com.aiku.data.api.remote.TokenApi
import com.aiku.data.dto.TokenDto
import com.aiku.data.dto.group.request.IssueATRTRequest
import com.aiku.data.dto.group.request.IssueATRequest
import com.aiku.domain.exception.ERROR_AUTO_LOGIN
import com.aiku.domain.exception.ERROR_KAKAO_EMAIL_FETCH
import com.aiku.domain.exception.ERROR_KAKAO_OIDC
import com.aiku.domain.exception.ERROR_KAKAO_SERVER
import com.aiku.domain.exception.ERROR_USER_NOT_FOUND
import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.TokenIssueErrorResponse
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LoginRemoteDataSource @Inject constructor(
    @ActivityContext private val context: Context,
    private val coroutineDispatcher: CoroutineDispatcher,
    private val tokenApi: TokenApi,
    private val noAuthTokenApi: NoAuthTokenApi
) {

    suspend fun loginWithKakaoTalk(): TokenDto {
        return withContext(coroutineDispatcher) {
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
            } catch (e: ErrorResponse) {
                throw e
            }
        }
    }

    suspend fun loginWithKakaoAccount(): TokenDto {
        return withContext(coroutineDispatcher) {
            try {
                performLogin { callback ->
                    UserApiClient.instance.loginWithKakaoAccount(
                        context,
                        callback = callback
                    )
                }
            } catch (e: ErrorResponse) {
                throw e
            }
        }
    }

    private suspend fun performLogin(
        loginAction: (callback: (token: OAuthToken?, error: Throwable?) -> Unit) -> Unit
    ): TokenDto {
        return try {
            /** 카카오 로그인 */
            val tokenResult = suspendCoroutine<OAuthToken?> { continuation ->
                loginAction { token, error ->
                    if (error != null) {
                        continuation.resumeWith(Result.failure(error))
                    } else {
                        continuation.resumeWith(Result.success(token))
                    }
                }
            }

            val idToken = tokenResult?.idToken ?: throw ErrorResponse(ERROR_KAKAO_OIDC, "카카오 OIDC 발급 실패")
            val email = getUserEmail()

            /** AT, RT 발행 */
            try {
                noAuthTokenApi.issueATRT(request = IssueATRTRequest(idToken))
            } catch (e: Exception) {
                if (e is ErrorResponse && e.code == ERROR_USER_NOT_FOUND) {
                    throw TokenIssueErrorResponse(ERROR_USER_NOT_FOUND, e.message, e.requestId, idToken, email)
                }
                throw e
            }

        } catch (e: Exception) {
            when (e) {
                is TokenIssueErrorResponse, is ErrorResponse -> { throw e }
                else -> throw ErrorResponse(ERROR_KAKAO_SERVER, "카카오 서버 연결 실패: ${e.message}")
            }
        }
    }


    //RT -> AT 재발급
    suspend fun loginWithToken(refreshToken: String, accessToken: String): TokenDto {
        return withContext(coroutineDispatcher) {
            try {
                tokenApi.issueAT(IssueATRequest(refreshToken, accessToken))
            } catch (e: Exception) {
                throw ErrorResponse(ERROR_AUTO_LOGIN, "Failed to refresh token: ${e.message}")
            }
        }
    }

    /** 카카오 이메일 fetch */
    private suspend fun getUserEmail(): String = withContext(coroutineDispatcher) {
        suspendCoroutine { continuation ->
            UserApiClient.instance.me { user, error ->
                when {
                    error != null -> continuation.resumeWithException(ErrorResponse(ERROR_KAKAO_EMAIL_FETCH, "카카오 이메일 fetch 실패 : ${error.message}"))
                    user?.kakaoAccount?.email != null -> continuation.resume(user.kakaoAccount?.email!!)
                    else -> continuation.resumeWithException(ErrorResponse(ERROR_KAKAO_EMAIL_FETCH, "카카오 이메일 fetch 실패 : 저장된 이메일이 없습니다."))
                }
            }
        }
    }
}

