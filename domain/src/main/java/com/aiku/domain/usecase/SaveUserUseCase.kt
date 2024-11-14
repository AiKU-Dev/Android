package com.aiku.domain.usecase

import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.INVALID_NICKNAME_FORMAT
import com.aiku.domain.exception.NICKNAME_LENGTH_EXCEED
import com.aiku.domain.exception.REQUIRE_NICKNAME_INPUT
import com.aiku.domain.model.user.NewUser
import com.aiku.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: NewUser): Result<Unit> {
        return when {
            user.isNicknameEmpty() -> Result.failure(ErrorResponse(REQUIRE_NICKNAME_INPUT))
            user.isNicknameLengthExceed(MAX_NICKNAME_LENGTH) -> Result.failure(ErrorResponse(NICKNAME_LENGTH_EXCEED))
            user.isInvalidNicknameFormat() -> Result.failure(ErrorResponse(INVALID_NICKNAME_FORMAT))
            else -> userRepository.saveUser(user)
        }
    }

    companion object {
        const val MAX_NICKNAME_LENGTH = 6
    }
}