package com.aiku.domain.usecase.schedule

import androidx.paging.PagingData
import com.aiku.domain.model.schedule.UserScheduleOverview
import com.aiku.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

class FetchUserScheduledDatesUseCase(
    private val scheduleRepository: ScheduleRepository
) {
    operator fun invoke(year: Int, month: Int): Flow<List<LocalDate>> {
        return scheduleRepository.fetchUserScheduledDates(year, month)
    }
}
