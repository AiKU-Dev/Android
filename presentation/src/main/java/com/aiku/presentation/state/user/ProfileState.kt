package com.aiku.presentation.state.user

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.aiku.domain.model.group.type.ProfileBackground
import com.aiku.domain.model.group.type.ProfileCharacter
import com.aiku.domain.model.group.type.ProfileType
import com.aiku.domain.model.user.Profile
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Immutable
data class ProfileState(
    val type: ProfileType,
    val image: String,
    val character: ProfileCharacter,
    val background: ProfileBackground
) : Parcelable {

    fun toProfile(): Profile = Profile(
        type = type,
        image = image,
        character = character,
        background = background
    )

    companion object {
        val EMPTY = ProfileState(
            type = ProfileType.CHAR,
            image = "",
            character = ProfileCharacter.C01,
            background = ProfileBackground.GREEN
        )
    }
}

fun Profile.toProfileState(): ProfileState = ProfileState(
    type = type,
    image = image,
    character = character,
    background = background
)
