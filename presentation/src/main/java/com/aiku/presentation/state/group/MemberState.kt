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

    fun toMember(): Member = Member(
        id = id,
        nickname = nickname,
        profile = profile.toProfile()
    )
}

fun Member.toMemberState(): MemberState = MemberState(
    id = id,
    nickname = nickname,
    profile = profile.toProfileState()
)
