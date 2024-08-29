package com.aiku.data.repository

import android.util.Log
import com.aiku.data.source.remote.LoginRemoteDataSource
import com.aiku.domain.model.UserModel
import com.aiku.domain.model.response.LoginResult
import com.aiku.domain.repository.LoginRepository
import com.kakao.sdk.auth.TokenManagerProvider
import com.kakao.sdk.user.UserApiClient // Kakao SDK 추가
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override suspend fun loginWithKakaoTalk(): Flow<LoginResult> = flow {
        try {
            val response = loginRemoteDataSource.loginWithKakaoTalk()
            if (response.token != null) {
                // OIDC id token
                val idToken = TokenManagerProvider.instance.manager.getToken()?.idToken
                // TODO : 서버로 idToken 전달
                //  -> 회원가입/로그인 진행
                //  -> 서버에서 받은 답변을 LoginResult에 저장

                // 로그인 성공 후 사용자 정보 요청
                val user = fetchKakaoUserInfo()
                emit(LoginResult(token = response.token, user = user))
            } else {
                emit(LoginResult(error = response.error))
            }
        } catch (e: Exception) {
            emit(LoginResult(error = e))
        }
    }.catch { e ->
        emit(LoginResult(error = e))
    }

    override suspend fun loginWithKakaoAccount(): Flow<LoginResult> = flow {
        try {
            val response = loginRemoteDataSource.loginWithKakaoAccount()
            if (response.token != null) {
                // OIDC id token
                val idToken = TokenManagerProvider.instance.manager.getToken()?.idToken
                // TODO : 서버로 idToken 전달
                //  -> 회원가입/로그인 진행
                //  -> 서버에서 받은 답변을 LoginResult에 저장

                // 로그인 성공 후 사용자 정보 요청
                val user = fetchKakaoUserInfo()
                emit(LoginResult(token = response.token, user = user))
            } else {
                emit(LoginResult(error = response.error))
            }
        } catch (e: Exception) {
            emit(LoginResult(error = e))
        }
    }.catch { e ->
        emit(LoginResult(error = e))
    }

    private suspend fun fetchKakaoUserInfo(): UserModel = suspendCancellableCoroutine { continuation ->
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                continuation.resumeWithException(error)
            } else if (user != null) {
                val userModel = UserModel(
                    id = "${user.id}",
                    email = "${user.kakaoAccount?.email}",
                    nickname = "${user.kakaoAccount?.profile?.nickname}",
                    profileImg = "${user.kakaoAccount?.profile?.thumbnailImageUrl}",
                    phoneNumber = "${user.kakaoAccount?.phoneNumber}"
                )
                continuation.resume(userModel)
            }
        }
    }
}
