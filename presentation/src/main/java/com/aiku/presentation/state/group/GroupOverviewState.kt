package com.aiku.presentation.state.group

import com.aiku.domain.model.group.GroupOverviewPagination
import java.time.LocalDateTime

data class GroupOverviewPaginationState(
    val page: Int,
    val data: List<GroupOverviewState>,
) {

    data class GroupOverviewState(
        val id: Long,
        val name: String,
        val memberSize: Int,
        val lastScheduleTime: LocalDateTime,  // 2024-01-01T00:00:00
    )
}

fun GroupOverviewPagination.toGroupOverviewPaginationState(): GroupOverviewPaginationState =
    GroupOverviewPaginationState(
        page = page,
        data = data.map { it.toGroupOverviewState() }
    )

fun GroupOverviewPagination.GroupOverview.toGroupOverviewState(): GroupOverviewPaginationState.GroupOverviewState =
    GroupOverviewPaginationState.GroupOverviewState(
        id = id,
        name = name,
        memberSize = memberSize,
        lastScheduleTime = lastScheduleTime
    )