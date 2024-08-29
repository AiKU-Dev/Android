package com.aiku.domain.usecase

import com.aiku.domain.model.User
import com.aiku.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(user: User): Flow<Unit> {
        // 유효한 닉네임 입력인지 검사 --> throw ErrorResponse(INVALID_NICKNAME_FORMAT, "닉네임 형식이 올바르지 않습니다.")
        // 유효한 전화번호 입력인지 검사 --> throw ErrorResponse(INVALID_PHONE_NUMBER_FORMAT, "전화번호 형식이 올바르지 않습니다.")
        return userRepository.saveUser(user)
    }
}