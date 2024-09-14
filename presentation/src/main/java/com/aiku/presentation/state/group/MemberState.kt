package com.aiku.presentation.state.group

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.aiku.domain.model.group.Member
import com.aiku.domain.model.group.type.ProfileBackground
import com.aiku.domain.model.group.type.ProfileCharacter
import com.aiku.domain.model.group.type.ProfileType
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class MemberState(
    val id: Long,
    val nickname: String,
    val profile: ProfileState
) : Parcelable {

    @Parcelize
    data class ProfileState(
        val type: ProfileType,
        val image: String,
        val character: ProfileCharacter,
        val background: ProfileBackground
    ) : Parcelable
}

fun Member.toMemberState(): MemberState = MemberState(
    id = id,
    nickname = nickname,
    profile = profile.toProfileState()
)

fun Member.Profile.toProfileState(): MemberState.ProfileState = MemberState.ProfileState(
    type = type,
    image = image,
    character = character,
    background = background
)