package com.aiku.presentation.state.user

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.aiku.domain.model.user.Badge
import com.aiku.domain.model.user.Profile
import com.aiku.domain.model.user.User
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class UserState(
    val id: Long,
    val nickname: String,
    val kakaoId: String,
    val profile: ProfileState,
    val badge: BadgeState,
    val point: Int,
): Parcelable {

    fun toUser(): User = User(
        id = id,
        nickname = nickname,
        kakaoId = kakaoId,
        profile = profile.toProfile(),
        badge = badge.toBadge(),
        point = point
    )
}

fun User.toUserState(): UserState = UserState(
    id = id,
    nickname = nickname,
    kakaoId = kakaoId,
    profile = profile.toProfileState(),
    badge = badge.toBadgeState(),
    point = point
)