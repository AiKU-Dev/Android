package com.aiku.presentation.navigation.route

import com.aiku.presentation.state.group.GroupState
import com.aiku.presentation.state.schedule.GroupScheduleOverviewState
import kotlinx.serialization.Serializable

@Serializable
object Routes {

    @Serializable
    object Splash

    @Serializable
    object Auth {
        @Serializable
        object Graph

        @Serializable
        object Login

        @Serializable
        object TermAgreement

        @Serializable
        data class TermContent(val identifier: Int)

        @Serializable
        object ProfileEdit
    }

    object Main {
        @Serializable
        object Graph

        @Serializable
        object Home

        @Serializable
        object MySchedule

        @Serializable
        object MyPage

        @Serializable
        data class Group(val groupId: Long, val groupName: String)

        @Serializable
        data class ScheduleWaiting(
            @Serializable val group: GroupState,
            @Serializable val groupScheduleOverview: GroupScheduleOverviewState
        )

        @Serializable
        object ScheduleRunning

        object CreateSchedule {
            @Serializable
            object Graph

            @Serializable
            object First
            // TODO : 약속 생성 Route 추가
        }

        @Serializable
        object Notification

        @Serializable
        object Shop
    }
}