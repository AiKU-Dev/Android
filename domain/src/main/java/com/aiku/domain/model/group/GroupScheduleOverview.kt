package com.aiku.domain.model.group

import com.aiku.domain.model.Location
import com.aiku.domain.model.group.type.ScheduleStatus
import java.time.LocalDateTime

data class GroupScheduleOverview(
    val id: Long,
    val name: String,
    val location: Location,
    val scheduleTime: LocalDateTime,
    val status: ScheduleStatus,
    val accept: Boolean,
)