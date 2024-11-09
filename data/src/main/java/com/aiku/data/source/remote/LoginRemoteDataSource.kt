package com.aiku.data.source.remote

import android.content.Context
import com.aiku.data.api.remote.TokenApi
import com.aiku.data.dto.TokenDto
import com.aiku.data.dto.group.request.IssueATRTRequest
import com.aiku.domain.exception.ERROR_AUTO_LOGIN
import com.aiku.domain.exception.ERROR_KAKAO_LOGIN
import com.aiku.domain.exception.ERROR_KAKAO_USER_INFO_FETCH
import com.aiku.domain.exception.ERROR_OCID_FETCH
import com.aiku.domain.exception.ErrorResponse
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
    private val tokenApi: TokenApi
) {

    suspend fun loginWithKakaoTalk(): TokenDto {
        return withContext(coroutineDispatcher) {
            try {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                    performLogin { callback -> UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
                    }
                } else {
                    loginWithKakaoAccount()
                }
            } catch (e: ErrorResponse) { throw e }
        }
    }

    suspend fun loginWithKakaoAccount(): TokenDto {
        return withContext(coroutineDispatcher) {
            try { performLogin { callback -> UserApiClient.instance.loginWithKakaoAccount(context, callback = callback) }
            } catch (e: ErrorResponse) { throw e }
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
            val idToken = tokenResult?.idToken ?: throw ErrorResponse(ERROR_OCID_FETCH, "idToken 발급 실패")
            tokenApi.issueATRT(request = IssueATRTRequest(idToken))

        } catch (e: Exception) { throw ErrorResponse(ERROR_KAKAO_LOGIN, "An error occurred: ${e.message}") }
    }

    //RT -> AT 재발급
    suspend fun loginWithToken(refreshToken: String, accessToken: String): TokenDto {
        return withContext(coroutineDispatcher) {
            try {
                // 임시로 쓰레기값을 반환
                val fakeTokenDto = TokenDto(
                    grantType = "Bearer",
                    accessToken = "fakeAccessToken1234",
                    refreshToken = "fakeRefreshToken1234"
                )
                fakeTokenDto
                // TODO : authApi.issueAT(IssueATRequest(refreshToken, accessToken)) + 서버 에러 세분화
            } catch (e: Exception) { throw ErrorResponse(ERROR_AUTO_LOGIN, "Failed to refresh token: ${e.message}") }
        }
    }

    // 이메일 정보 가져오기
    suspend fun getUserEmail(): String = withContext(coroutineDispatcher) {
        suspendCoroutine { continuation ->
            UserApiClient.instance.me { user, error ->
                when {
                    error != null -> continuation.resumeWithException(ErrorResponse(ERROR_KAKAO_USER_INFO_FETCH, "Failed to fetch user email: ${error.message}"))
                    user?.kakaoAccount?.email != null -> continuation.resume(user.kakaoAccount?.email!!)
                    else -> continuation.resumeWithException(ErrorResponse( ERROR_KAKAO_USER_INFO_FETCH, "No email provided"))
                }
            }
        }
    }
}

