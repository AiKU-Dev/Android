package com.aiku.data.api.remote

import com.aiku.data.dto.IdDto
import com.aiku.data.dto.schedule.GroupScheduleOverviewPaginationDto
import com.aiku.data.dto.schedule.ScheduleRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDateTime

interface ScheduleApi {

    @POST("groups/{groupId}/schedules")
    suspend fun createSchedule(@Path("groupId") groupId: Long, @Body scheduleRequest: ScheduleRequest): IdDto

    @PATCH("groups/{groupId}/schedules/{scheduleId}")
    suspend fun updateSchedule(@Path("groupId") groupId: Long, @Path("scheduleId") scheduleId: Long, @Body scheduleRequest: ScheduleRequest): IdDto

    @DELETE("groups/{groupId}/schedules/{scheduleId}")
    suspend fun deleteSchedule(@Path("groupId") groupId: Long, @Path("scheduleId") scheduleId: Long): IdDto

    @GET("groups/{groupId}/schedules")
    suspend fun fetchGroupSchedules(
        @Path("groupId") groupId: Long,
        @Query("page") page: Int,           // 페이지당 10개
        @Query("startDate") startDate: LocalDateTime,
        @Query("endDate") endDate: LocalDateTime
    ): List<GroupScheduleOverviewPaginationDto>
}