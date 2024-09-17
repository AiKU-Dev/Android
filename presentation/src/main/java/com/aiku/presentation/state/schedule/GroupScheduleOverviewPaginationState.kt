package com.aiku.presentation.state.schedule

import androidx.compose.runtime.Immutable
import com.aiku.domain.model.schedule.GroupScheduleOverviewPagination

@Immutable
data class GroupScheduleOverviewPaginationState(
    val totalCount: Long,
    val page: Int,
    val groupId: Long,
    val runningSchedule: Int,
    val waitingSchedule: Int,
    val groupScheduleOverview: GroupScheduleOverviewState
)

fun GroupScheduleOverviewPagination.toGroupScheduleOverviewPaginationState() =
    GroupScheduleOverviewPaginationState(
        totalCount,
        page,
        groupId,
        runningSchedule,
        waitingSchedule,
        groupScheduleOverview.toGroupScheduleOverviewState()
    )
