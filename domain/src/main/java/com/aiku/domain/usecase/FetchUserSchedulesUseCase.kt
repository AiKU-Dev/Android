package com.aiku.domain.usecase

import com.aiku.domain.model.schedule.UserScheduleOverviewPagination
import com.aiku.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

class FetchUserSchedulesUseCase(
    private val scheduleRepository: ScheduleRepository
) {
    fun execute(
        page: Int,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<UserScheduleOverviewPagination> {
        return scheduleRepository.fetchUserSchedules(page, startDate, endDate)
    }
}
