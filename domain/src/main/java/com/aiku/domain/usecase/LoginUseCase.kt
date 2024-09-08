package com.aiku.domain.usecase

import com.aiku.domain.repository.AuthRepository
import com.aiku.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val authRepository: AuthRepository ){

    // 카카오톡으로 로그인
    private fun loginWithKakaoTalk(): Flow<String?> {
        return loginRepository.loginWithKakaoTalk()
    }

    // 카카오 계정으로 로그인
    private fun loginWithKakaoAccount(): Flow<String?> {
        return loginRepository.loginWithKakaoAccount()
    }

    // 로그인 방법 선택
    fun execute(useKakaoTalk: Boolean): Flow<String?> {
        return if (useKakaoTalk) {
            loginWithKakaoTalk()
        } else {
            loginWithKakaoAccount()
        }
    }

    // 자동 로그인
    suspend fun autoLogin(): Flow<String?>? {
        val savedToken = authRepository.getAuthToken().first()
        return if (savedToken != null) {
            loginRepository.loginWithToken(savedToken)
        } else {
            null
        }
    }

    fun getToken() = authRepository.getAuthToken()

    suspend fun logout() {
        authRepository.clearAuthToken()
    }
}
