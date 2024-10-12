package com.aiku.data.repository

import com.aiku.data.source.remote.CreateScheduleRemoteDataSource
import com.aiku.domain.model.schedule.Location
import com.aiku.domain.repository.CreateScheduleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CreateScheduleRepositoryImpl @Inject constructor(
    private val createScheduleRemoteDataSource: CreateScheduleRemoteDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) : CreateScheduleRepository {
    override suspend fun searchLocations(query: String): Flow<List<Location>> {
        TODO("Not yet implemented")
    }

    override suspend fun getLocation(latitude: Double, longitude: Double): Flow<Location> = flow {
        val locationDto = createScheduleRemoteDataSource.getLocation(latitude, longitude)
        emit(locationDto.toLocation(latitude, longitude))
    }.catch { e -> throw e
    }.flowOn(coroutineDispatcher)

}
