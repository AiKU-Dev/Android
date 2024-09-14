package com.aiku.presentation.state.group

import androidx.compose.runtime.Immutable
import com.aiku.domain.model.group.GroupScheduleOverviewPagination

@Immutable
data class GroupScheduleOverviewPaginationState(
    val page: Int,
    val groupId: Long,
    val runSchedule: Int,
    val waitSchedule: Int,
    val data: List<GroupScheduleOverviewState>
)

fun GroupScheduleOverviewPagination.toGroupScheduleOverviewPaginationState() =
    GroupScheduleOverviewPaginationState(
        page = page,
        groupId = groupId,
        runSchedule = runSchedule,
        waitSchedule = waitSchedule,
        data = data.map { it.toGroupScheduleOverviewState() }
    )