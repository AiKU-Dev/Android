package com.aiku.domain.model

data class Member(
    val id: Long,
    val nickname: String,
    val profile: Profile
) {

    data class Profile(
        val type: ProfileType,
        val image: String,
        val character: ProfileCharacter,
        val background: ProfileBackground
    )
}

enum class ProfileType {
    IMG, CHAR
}

enum class ProfileCharacter {
    C01, C02, C03, C04, NONE
}

enum class ProfileBackground {
    RED, GREEN, BLUE, NONE
}