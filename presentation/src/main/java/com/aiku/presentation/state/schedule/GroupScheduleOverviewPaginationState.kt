package com.aiku.presentation.state.schedule

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.aiku.domain.model.schedule.GroupScheduleOverviewPagination
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class GroupScheduleOverviewPaginationState(
    val totalCount: Long,
    val page: Int,
    val groupId: Long,
    val runningSchedule: Int,
    val waitingSchedule: Int,
    val groupScheduleOverview: List<GroupScheduleOverviewState>
) : Parcelable

fun GroupScheduleOverviewPagination.toGroupScheduleOverviewPaginationState() =
    GroupScheduleOverviewPaginationState(
        totalCount,
        page,
        groupId,
        runningSchedule,
        waitingSchedule,
        groupScheduleOverview.map { it.toGroupScheduleOverviewState() }
    )
