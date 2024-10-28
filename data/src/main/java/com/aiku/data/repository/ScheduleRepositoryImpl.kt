package com.aiku.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aiku.data.dto.schedule.request.toCreateScheduleReqDto
import com.aiku.data.source.remote.ScheduleRemoteDataSource
import com.aiku.data.util.createPager
import com.aiku.domain.model.schedule.GroupScheduleOverviewPagination
import com.aiku.domain.model.schedule.UserScheduleOverview
import com.aiku.domain.model.schedule.request.CreateScheduleReq
import com.aiku.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleRemoteDateSource: ScheduleRemoteDataSource
) : ScheduleRepository {
    override fun createSchedule(
        groupId: Long,
        createScheduleReq: CreateScheduleReq
    ): Flow<Long> {
        return flow {
            emit(
                scheduleRemoteDateSource.createSchedule(
                    groupId,
                    createScheduleReq.toCreateScheduleReqDto()
                ).id ?: 0
            )
        }
    }

    override fun fetchGroupSchedules(
        groupId: Long,
        page: Int,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<GroupScheduleOverviewPagination> {
        return flow {
            emit(scheduleRemoteDateSource.fetchGroupSchedules(
                groupId, page, startDate, endDate
            ).toGroupScheduleOverviewPagination())
        }
    }

    override fun fetchUserSchedules(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<PagingData<UserScheduleOverview>> {
        return createPager(
            pageSize = 10,
            loadData = { page ->
                val dto = scheduleRemoteDateSource.fetchUserSchedules(page, startDate, endDate)
                val pagination = dto.toUserScheduleOverviewPagination()
                pagination.userScheduleOverview
            }
        )
    }
}