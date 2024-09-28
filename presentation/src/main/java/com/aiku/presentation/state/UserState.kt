package com.aiku.presentation.state

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.aiku.domain.model.User
import com.aiku.presentation.state.group.GroupState
import com.aiku.presentation.state.group.toGroupState
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class UserState(
    val image: String,
    val nickname: String,
    val phoneNumber: String
): Parcelable {

    fun toUser() = User(
        image = image,
        nickname = nickname,
        phoneNumber = phoneNumber
    )
}

fun User.toUserState() = UserState(
    image = image,
    nickname = nickname,
    phoneNumber = phoneNumber
)