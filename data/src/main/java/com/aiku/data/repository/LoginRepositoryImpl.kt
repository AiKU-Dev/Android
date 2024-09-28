package com.aiku.data.repository

import android.util.Log
import com.aiku.data.dto.TokenDto
import com.aiku.data.source.remote.LoginRemoteDataSource
import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.UNKNOWN
import com.aiku.domain.model.Token
import com.aiku.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) : LoginRepository {

    override fun loginWithKakaoTalk(): Flow<Token> = flow {
        val tokenDto = loginRemoteDataSource.loginWithKakaoTalk()
        emit(tokenDto.toToken())
    }.catch { e ->
        throw ErrorResponse(UNKNOWN, e.message ?: "Unknown error occurred")
    }.flowOn(coroutineDispatcher)

    override fun loginWithKakaoAccount(): Flow<Token> = flow {
        val tokenDto = loginRemoteDataSource.loginWithKakaoAccount()
        emit(tokenDto.toToken())
    }.catch { e ->
        throw ErrorResponse(UNKNOWN, e.message ?: "Unknown error occurred during KakaoAccount login")
    }.flowOn(coroutineDispatcher)

    override fun loginWithToken(refreshToken: String): Flow<Token> = flow {
        val tokenDto = loginRemoteDataSource.loginWithToken(refreshToken)
        emit(tokenDto.toToken())
    }.catch { e ->
        throw ErrorResponse(UNKNOWN, e.message ?: "Unknown error occurred during token refresh")
    }.flowOn(coroutineDispatcher)
}
