package com.aiku.data.dto.user

import com.aiku.domain.model.user.NewUser
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewUserDto(
    @Json(name = "nickname") val nickname: String?,
    @Json(name = "memberProfile") val profile: ProfileDto?,
    @Json(name = "email") val email: String?,
    @Json(name = "idToken") val idToken: String?,
    @Json(name = "isServicePolicyAgreed") val isServicePolicyAgreed: Boolean?,
    @Json(name = "isPersonalInformationPolicyAgreed") val isPersonalInformationPolicyAgreed: Boolean?,
    @Json(name = "isLocationPolicyAgreed") val isLocationPolicyAgreed: Boolean?,
    @Json(name = "isMarketingPolicyAgreed") val isMarketingPolicyAgreed: Boolean?,
    @Json(name = "recommenderNickname") val recommenderNickname: String?,
)

fun NewUser.toNewUserDto() = NewUserDto(
    nickname = nickname,
    profile = profile.toProfileDto(),
    email = email,
    idToken = idToken,
    isServicePolicyAgreed = isServicePolicyAgreed,
    isPersonalInformationPolicyAgreed = isPersonalInformationPolicyAgreed,
    isLocationPolicyAgreed = isLocationPolicyAgreed,
    isMarketingPolicyAgreed = isMarketingPolicyAgreed,
    recommenderNickname = recommenderNickname
)

