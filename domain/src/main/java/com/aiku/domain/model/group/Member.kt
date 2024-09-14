package com.aiku.domain.model.group

import com.aiku.domain.model.group.type.ProfileBackground
import com.aiku.domain.model.group.type.ProfileCharacter
import com.aiku.domain.model.group.type.ProfileType

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
