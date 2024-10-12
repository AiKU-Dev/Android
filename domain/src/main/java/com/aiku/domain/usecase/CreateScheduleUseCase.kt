package com.aiku.domain.usecase

import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.TOKEN_NOT_FOUND
import com.aiku.domain.exception.UNKNOWN
import com.aiku.domain.model.schedule.Location
import com.aiku.domain.repository.CreateScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateScheduleUseCase @Inject constructor(
    private val createScheduleRepository: CreateScheduleRepository
) {
    companion object {
        const val MAX_SCHEDULENAME_LENGTH = 15
    }

//    fun execute(scheduleName: String): Flow<Result<Schedule>> {
//        return flow {
//            if (scheduleName.length > MAX_SCHEDULENAME_LENGTH) {
//                throw ErrorResponse(INVALID_INPUT, "약속 이름이 너무 깁니다.")
//            }
//            scheduleRepository.createSchedule(scheduleName).collect { result ->
//                emit(result)
//            }
//        }
//    }

    // 장소 검색
    suspend fun searchLocations(query: String): Flow<List<Location>> {
        return createScheduleRepository.searchLocations(query)
    }

    // 좌표 -> 장소이름
    suspend fun getLocation(latitude: Double, longitude: Double): Flow<Location> {
        val location = createScheduleRepository.getLocation(latitude, longitude)
        return location
    }
}