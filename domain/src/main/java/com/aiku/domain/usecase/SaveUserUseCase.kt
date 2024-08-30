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

    // TODO : 메시지를 Domain에 두는 것이 맞는가?
    operator fun invoke(user: User): Flow<Unit> {
        if (user.name.isEmpty())
            throw ErrorResponse(REQUIRE_NICKNAME_INPUT, "닉네임을 입력해주세요.")

        if (user.phoneNumber.isEmpty())
            throw ErrorResponse(REQUIRE_PHONE_NUMBER_INPUT, "전화번호를 입력해주세요.")

        if (user.name.length > MAX_NICKNAME_LENGTH)
            throw ErrorResponse(NICKNAME_LENGTH_EXCEED, "닉네임은 5자 이하로 입력해주세요.")

        if (RegexUtil.isValidNickname(user.name).not())
            throw ErrorResponse(INVALID_NICKNAME_FORMAT, "닉네임 형식이 올바르지 않습니다.")

        if (RegexUtil.isValidPhoneNumber(user.phoneNumber).not())
            throw ErrorResponse(INVALID_PHONE_NUMBER_FORMAT, "전화번호 형식이 올바르지 않습니다.")

        return userRepository.saveUser(user)
    }

    companion object {
        const val MAX_NICKNAME_LENGTH = 5
    }
}