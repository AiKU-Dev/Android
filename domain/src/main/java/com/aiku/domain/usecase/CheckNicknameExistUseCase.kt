package com.aiku.domain.usecase

import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.NICKNAME_LENGTH_EXCEED
import com.aiku.domain.exception.REQUIRE_NICKNAME_INPUT
import com.aiku.domain.repository.UserRepository
import javax.inject.Inject

class CheckNicknameExistUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(nickname: String): Result<Unit> {
        return when {
            nickname.isEmpty() -> Result.failure(ErrorResponse(REQUIRE_NICKNAME_INPUT))
            nickname.length > SaveUserUseCase.MAX_NICKNAME_LENGTH -> Result.failure(ErrorResponse(NICKNAME_LENGTH_EXCEED))
            else -> userRepository.fetchIsNicknameExist(nickname)
        }
    }
}