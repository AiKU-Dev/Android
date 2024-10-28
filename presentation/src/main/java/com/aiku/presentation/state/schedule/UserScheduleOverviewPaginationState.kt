package com.aiku.presentation.state.schedule

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.aiku.domain.model.schedule.GroupScheduleOverviewPagination
import com.aiku.domain.model.schedule.UserScheduleOverviewPagination
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class UserScheduleOverviewPaginationState(
    val page: Int,
    val runningSchedule: Int,
    val waitingSchedule: Int,
    val userScheduleOverview: List<UserScheduleOverviewState>
) : Parcelable

fun UserScheduleOverviewPagination.toUserScheduleOverviewPaginationState() =
    UserScheduleOverviewPaginationState(
        page,
        runningSchedule,
        waitingSchedule,
        userScheduleOverview.map { it.toUserScheduleOverviewState() }
    )
