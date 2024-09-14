package com.aiku.presentation.state.group

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.aiku.domain.model.group.Group
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class GroupState(
    val id: Long,
    val name: String,
    val members: List<MemberState>,
) : Parcelable

fun Group.toGroupState(): GroupState = GroupState(
    id = id,
    name = name,
    members = members.map { it.toMemberState() }
)