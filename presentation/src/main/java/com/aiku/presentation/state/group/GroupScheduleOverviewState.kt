package com.aiku.presentation.state.group

import androidx.compose.runtime.Immutable
import com.aiku.domain.model.Location
import com.aiku.domain.model.group.GroupScheduleOverview
import com.aiku.domain.model.group.type.ScheduleStatus
import java.time.LocalDateTime

@Immutable
data class GroupScheduleOverviewState(
    val id: Long,
    val name: String,
    val location: Location,
    val scheduleTime: LocalDateTime,
    val status: ScheduleStatus,
    val accept: Boolean,
)

fun GroupScheduleOverview.toGroupScheduleOverviewState() =
    GroupScheduleOverviewState(
        id = id,
        name = name,
        location = location,
        scheduleTime = scheduleTime,
        status = status,
        accept = accept,
    )
