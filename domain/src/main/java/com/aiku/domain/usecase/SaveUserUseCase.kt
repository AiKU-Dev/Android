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
        if (user.name.isEmpty())
            throw ErrorResponse(REQUIRE_NICKNAME_INPUT)

        if (user.phoneNumber.isEmpty())
            throw ErrorResponse(REQUIRE_PHONE_NUMBER_INPUT)

        if (user.name.length > MAX_NICKNAME_LENGTH)
            throw ErrorResponse(NICKNAME_LENGTH_EXCEED)

        if (RegexUtil.isValidNickname(user.name).not())
            throw ErrorResponse(INVALID_NICKNAME_FORMAT)

        if (RegexUtil.isValidPhoneNumber(user.phoneNumber).not())
            throw ErrorResponse(INVALID_PHONE_NUMBER_FORMAT)

        return userRepository.saveUser(user)
    }

    companion object {
        const val MAX_NICKNAME_LENGTH = 5
    }
}