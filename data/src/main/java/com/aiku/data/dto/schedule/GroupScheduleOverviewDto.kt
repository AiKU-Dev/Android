package com.aiku.data.dto.schedule

import com.aiku.domain.model.Location
import com.aiku.domain.model.group.GroupScheduleOverview
import com.aiku.domain.model.group.type.ScheduleStatus
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime
import java.util.Locale

@JsonClass(generateAdapter = true)
data class GroupScheduleOverviewDto(
    @Json(name = "scheduleId") val id: Long?,
    @Json(name = "scheduleName") val name: String?,
    @Json(name = "location") val location: LocationDto?,
    @Json(name = "scheduleTime") val scheduleTime: LocalDateTime?,
    @Json(name = "scheduleStatus") val status: String?, // RUN, WAIT, TERM
    @Json(name = "accept") val accept: Boolean?,
) {

    fun toGroupScheduleOverview(): GroupScheduleOverview {
        return GroupScheduleOverview(
            id = id ?: 0,
            name = name ?: "",
            location = location?.toLocation() ?: Location(.0, .0, ""),
            scheduleTime = scheduleTime ?: LocalDateTime.MIN,
            status = ScheduleStatus.valueOf(status?.uppercase(Locale.ROOT) ?: "TERM"),
            accept = accept ?: false,
        )
    }
}