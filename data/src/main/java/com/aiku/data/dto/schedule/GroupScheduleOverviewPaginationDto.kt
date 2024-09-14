package com.aiku.data.dto.schedule

import com.aiku.domain.model.group.GroupScheduleOverviewPagination
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupScheduleOverviewPaginationDto(
    @Json(name = "page") val page: Int?,
    @Json(name = "groupId") val groupId: Long?,
    @Json(name = "runSchedule") val runSchedule: Int?,
    @Json(name = "waitSchedule") val waitSchedule: Int?,
    @Json(name = "data") val data: List<GroupScheduleOverviewDto>?
) {

    fun toGroupScheduleOverviewPagination(): GroupScheduleOverviewPagination {
        return GroupScheduleOverviewPagination(
            page = page ?: 1,
            groupId = groupId ?: 0,
            runSchedule = runSchedule ?: 0,
            waitSchedule = waitSchedule ?: 0,
            data = data?.map { it.toGroupScheduleOverview() } ?: emptyList()
        )
    }
}