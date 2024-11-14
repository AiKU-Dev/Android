package com.aiku.domain.model.user

import com.aiku.domain.util.RegexUtil

data class NewUser(
    val nickname: String,
    val profile: Profile,
    val email: String,
    val idToken: String,
    val isServicePolicyAgreed: Boolean,
    val isPersonalInformationPolicyAgreed: Boolean,
    val isLocationPolicyAgreed: Boolean,
    val isMarketingPolicyAgreed: Boolean,
    val recommenderNickname: String,
) {
    fun isNicknameEmpty() = nickname.isEmpty()
    fun isNicknameLengthExceed(max: Int) = nickname.length > max
    fun isInvalidNicknameFormat() = RegexUtil.isValidNickname(nickname).not()
}
