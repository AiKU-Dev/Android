package com.aiku.data.source.remote

import android.content.Context
import android.util.Log
import com.aiku.data.model.remote.LoginResponse
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class LoginRemoteDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : LoginRemoteDataSource {

    override suspend fun loginWithKakaoTalk(): LoginResponse {
        return withContext(Dispatchers.IO) {
            try {
                // 카카오톡이 설치되어 있는지 확인
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                    // 카카오톡으로 로그인 시도
                    return@withContext suspendCancellableCoroutine { continuation ->
                        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                            if (error != null) {
                                continuation.resumeWith(Result.failure(error))
                            } else if (token != null) {
                                continuation.resume(LoginResponse(token, null))
                            }
                        }
                    }
                } else {
                    // 카카오톡이 설치되어 있지 않으면 카카오 계정으로 로그인 시도
                    loginWithKakaoAccount()
                }
            } catch (e: Exception) {
                Log.e("LoginRemoteDataSourceImpl", "로그인 처리 중 오류 발생", e)
                LoginResponse(null, e)
            }
        }
    }

    override suspend fun loginWithKakaoAccount(): LoginResponse {
        return withContext(Dispatchers.IO) {
            try {
                suspendCancellableCoroutine<LoginResponse> { continuation ->
                    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                        if (error != null) {
                            continuation.resumeWith(Result.failure(error))
                        } else if (token != null) {
                            continuation.resume(LoginResponse(token, null))
                        } else {
                            continuation.resume(LoginResponse(null, Exception("Unknown error")))
                        }
                    }
                    // 코루틴 취소 시
                    continuation.invokeOnCancellation {
                        // 로그인 요청 취소 처리
                    }
                }
            } catch (e: Exception) {
                Log.e("LoginRemoteDataSourceImpl", "로그인 처리 중 오류 발생", e)
                LoginResponse(null, e)
            }
        }
    }

}
