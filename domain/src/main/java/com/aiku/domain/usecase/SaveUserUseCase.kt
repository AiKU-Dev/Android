package com.aiku.domain.usecase

import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.INVALID_NICKNAME_FORMAT
import com.aiku.domain.exception.INVALID_PHONE_NUMBER_FORMAT
import com.aiku.domain.exception.NICKNAME_LENGTH_EXCEED
import com.aiku.domain.exception.REQUIRE_NICKNAME_INPUT
import com.aiku.domain.exception.REQUIRE_PHONE_NUMBER_INPUT
import com.aiku.domain.model.User
import com.aiku.domain.repository.UserRepository
import com.aiku.domain.util.RegexUtil
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(user: User): Flow<Unit> {
        if (user.isNicknameEmpty())
            throw ErrorResponse(REQUIRE_NICKNAME_INPUT, "TODO")

        if (user.isPhoneNumberEmpty())
            throw ErrorResponse(REQUIRE_PHONE_NUMBER_INPUT, "TODO")

        if (user.isNicknameLengthExceed(MAX_NICKNAME_LENGTH))
            throw ErrorResponse(NICKNAME_LENGTH_EXCEED, "TODO")

        if (user.isInvalidNicknameFormat())
            throw ErrorResponse(INVALID_NICKNAME_FORMAT, "TODO")

        if (user.isInvalidPhoneNumberFormat())
            throw ErrorResponse(INVALID_PHONE_NUMBER_FORMAT, "TODO")

        return userRepository.saveUser(user)
    }

    companion object {
        const val MAX_NICKNAME_LENGTH = 6
    }
}