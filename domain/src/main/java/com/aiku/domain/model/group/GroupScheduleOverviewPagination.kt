package com.aiku.domain.model.group

data class GroupScheduleOverviewPagination(
    val page: Int,
    val groupId: Long,
    val runSchedule: Int,
    val waitSchedule: Int,
    val data: List<GroupScheduleOverview>
)