package com.aiku.data.dto.schedule

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScheduleRequest(
    @Json(name = "scheduleName") val scheduleName: String,
    @Json(name = "location") val location: LocationDto,
    @Json(name = "scheduleTime") val scheduleTime: String, // 2024-01-01T00:00:00.000
    @Json(name = "pointAmount") val pointAmount: String
)