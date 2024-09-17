package com.aiku.domain.model.schedule

data class GroupScheduleOverviewPagination(
    val totalCount: Long,
    val page: Int,
    val groupId: Long,
    val runningSchedule: Int,
    val waitingSchedule: Int,
    val groupScheduleOverview: GroupScheduleOverview
)