package com.aiku.presentation.state.schedule

import androidx.compose.runtime.Immutable
import com.aiku.domain.model.schedule.GroupScheduleOverview
import com.aiku.domain.model.schedule.Location
import com.aiku.domain.model.schedule.type.ScheduleStatus
import java.time.LocalDateTime

@Immutable
data class GroupScheduleOverviewState(
    val id: Long,
    val name: String,
    val location: Location,
    val time: LocalDateTime,
    val status: ScheduleStatus,
    val memberSize: Int,
    val accept: Boolean
)

fun GroupScheduleOverview.toGroupScheduleOverviewState() =
    GroupScheduleOverviewState(id, name, location, time, status, memberSize, accept)