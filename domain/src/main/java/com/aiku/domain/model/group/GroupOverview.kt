package com.aiku.domain.model.group

data class GroupOverviewPagination(
    val page: Int,
    val data: List<GroupOverview>,
) {

    data class GroupOverview(
        val id: Long,
        val name: String,
        val memberSize: Int,
        val lastScheduleTime: String,  // 2024-01-01T00:00:00
    )
}