package com.aiku.presentation.state

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.aiku.domain.model.Group
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class GroupState(
    val id: Long,
    val name: String,
    val description: String,
    val memberIDs: List<Long>,
) : Parcelable {

    fun toGroup() = Group(
        id = id,
        name = name,
        description = description,
        memberIDs = memberIDs
    )
}

fun Group.toGroupState() = GroupState(
    id = id,
    name = name,
    description = description,
    memberIDs = memberIDs
)