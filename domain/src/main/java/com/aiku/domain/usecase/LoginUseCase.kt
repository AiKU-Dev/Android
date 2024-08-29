package com.aiku.domain.usecase

import com.aiku.domain.model.response.LoginResult
import com.aiku.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    // 카카오톡으로 로그인
    private suspend fun loginWithKakaoTalk(): Flow<LoginResult> {
        return loginRepository.loginWithKakaoTalk()
    }

    // 카카오 계정으로 로그인
    private suspend fun loginWithKakaoAccount(): Flow<LoginResult> {
        return loginRepository.loginWithKakaoAccount()
    }

    // 로그인 방법 선택
    suspend fun execute(useKakaoTalk: Boolean): Flow<LoginResult> {
        return if (useKakaoTalk) {
            loginWithKakaoTalk()
        } else {
            loginWithKakaoAccount()
        }
    }
}

