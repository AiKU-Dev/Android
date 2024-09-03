package com.aiku.domain.model

import com.aiku.domain.util.RegexUtil

data class User(
    val image: String,
    val nickname: String,
    val phoneNumber: String,
    val groups: List<Group>,
) {
    fun isNicknameEmpty() = nickname.isEmpty()
    fun isPhoneNumberEmpty() = phoneNumber.isEmpty()
    fun isNicknameLengthExceed(max: Int) = nickname.length > max
    fun isInvalidNicknameFormat() = RegexUtil.isValidNickname(nickname).not()
    fun isInvalidPhoneNumberFormat() = RegexUtil.isValidPhoneNumber(phoneNumber).not()
}