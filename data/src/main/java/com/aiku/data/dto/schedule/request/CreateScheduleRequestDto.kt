package com.aiku.data.dto.schedule.request

import com.aiku.data.dto.schedule.LocationDto
import com.aiku.data.dto.schedule.toLocationDto
import com.aiku.domain.model.schedule.request.CreateScheduleRequest
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class CreateScheduleRequestDto(
    @Json(name = "scheduleName") val scheduleName: String,
    @Json(name = "location") val location: LocationDto,
    @Json(name = "scheduleTime") val scheduleTime: LocalDateTime,
    @Json(name = "pointAmount") val pointAmount: Int
)

fun CreateScheduleRequest.toCreateScheduleRequestDto() = CreateScheduleRequestDto(
    scheduleName, location.toLocationDto(), scheduleTime, pointAmount
)