package com.aiku.data.repository

import com.aiku.data.source.remote.LoginRemoteDataSource
import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override fun loginWithKakaoTalk(): Flow<String?> = flow {
        val response = loginRemoteDataSource.loginWithKakaoTalk()
        if (response.token != null) {
            emit(null) // 로그인 성공
        } else {
            throw response.error?.let { ErrorResponse(it.code, response.error.message) }!!
        }
    }

    override fun loginWithKakaoAccount(): Flow<String?> = flow {
        val response = loginRemoteDataSource.loginWithKakaoAccount()
        if (response.token != null) {
            emit(null) // 로그인 성공
        } else {
            throw response.error?.let { ErrorResponse(it.code, response.error.message) }!!
        }
    }

    override fun loginWithToken(refreshToken: String): Flow<String?> = flow {
        val response = loginRemoteDataSource.loginWithToken(refreshToken)
        if (response.success) {
            emit(response.data)
        } else {
            throw response.error?.let { ErrorResponse(it.code, response.error.message) }!!
        }
    }
}
