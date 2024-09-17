package com.aiku.data.dto.schedule

import com.aiku.domain.model.schedule.GroupScheduleOverviewPagination
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupScheduleOverviewPaginationDto(
    @Json(name = "totalCount") val totalCount: Long?,
    @Json(name = "page") val page: Int?,
    @Json(name = "groupId") val groupId: Long?,
    @Json(name = "runSchedule") val runningSchedule: Int?,
    @Json(name = "waitSchedule") val waitingSchedule: Int?,
    @Json(name = "data") val groupScheduleOverview: GroupScheduleOverviewDto?
) {

    fun toGroupScheduleOverviewPagination() = GroupScheduleOverviewPagination(
        totalCount = totalCount ?: 0,
        page = page ?: 1,
        groupId = groupId ?: 0,
        runningSchedule = runningSchedule ?: 0,
        waitingSchedule = waitingSchedule ?: 0,
        groupScheduleOverview = (groupScheduleOverview ?: GroupScheduleOverviewDto(
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )).toGroupScheduleOverview()
    )
}