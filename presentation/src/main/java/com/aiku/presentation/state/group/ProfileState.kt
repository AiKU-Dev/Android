package com.aiku.presentation.state.group

import android.os.Parcelable
import com.aiku.domain.model.group.Member
import com.aiku.domain.model.group.type.ProfileBackground
import com.aiku.domain.model.group.type.ProfileCharacter
import com.aiku.domain.model.group.type.ProfileType
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileState(
    val type: ProfileType,
    val image: String,
    val character: ProfileCharacter,
    val background: ProfileBackground
) : Parcelable {

    fun toProfile(): Member.Profile = Member.Profile(
        type = type,
        image = image,
        character = character,
        background = background
    )
}

fun Member.Profile.toProfileState(): ProfileState = ProfileState(
    type = type,
    image = image,
    character = character,
    background = background
)