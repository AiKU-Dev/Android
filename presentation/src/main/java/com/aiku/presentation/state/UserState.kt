package com.aiku.presentation.state

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.aiku.domain.model.User
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class UserState(
    val image: String,
    val name: String,
    val phoneNumber: String,
    val groups: List<GroupState>
): Parcelable {

    fun toUser() = User(
        image = image,
        nickname = name,
        phoneNumber = phoneNumber,
        groups = groups.map { it.toGroup() }
    )
}

fun User.toUserState() = UserState(
    image = image,
    name = nickname,
    phoneNumber = phoneNumber,
    groups = groups.map { it.toGroupState() }
)