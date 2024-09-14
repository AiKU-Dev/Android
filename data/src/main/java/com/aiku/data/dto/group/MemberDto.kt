package com.aiku.data.dto.group

import com.aiku.domain.model.group.Member
import com.aiku.domain.model.group.type.ProfileBackground
import com.aiku.domain.model.group.type.ProfileCharacter
import com.aiku.domain.model.group.type.ProfileType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Locale

@JsonClass(generateAdapter = true)
data class MemberDto(
    @Json(name = "memberId") val id: Long?,
    @Json(name = "nickname") val nickname: String?,
    @Json(name = "memberProfile") val profile: ProfileDto?
) {

    @JsonClass(generateAdapter = true)
    data class ProfileDto(
        @Json(name = "profileType") val type: String?,  // IMG or CHAR
        @Json(name = "profileImg") val image: String?,
        @Json(name = "profileCharacter") val character: String?, // C01, C02, C03, C04
        @Json(name = "profileBackground") val background: String? // RED, GREEN, BLUE
    ) {

        fun toProfile(): Member.Profile = Member.Profile(
            type = type?.let { ProfileType.valueOf(it.uppercase(Locale.ROOT)) } ?: ProfileType.IMG,
            image = image ?: "",
            character = character?.let { ProfileCharacter.valueOf(it.uppercase(Locale.ROOT)) }
                ?: ProfileCharacter.NONE,
            background = background?.let { ProfileBackground.valueOf(it.uppercase(Locale.ROOT)) }
                ?: ProfileBackground.NONE
        )
    }

    fun toMember(): Member = Member(
        id = id ?: 0,
        nickname = nickname ?: "",
        profile = profile?.toProfile() ?: Member.Profile(
            type = ProfileType.CHAR,
            image = "",
            character = ProfileCharacter.C01,
            background = ProfileBackground.GREEN
        )
    )
}