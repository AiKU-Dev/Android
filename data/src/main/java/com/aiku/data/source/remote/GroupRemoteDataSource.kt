package com.aiku.data.source.remote

import com.aiku.data.dto.IdDto
import com.aiku.data.dto.group.request.CreateGroupRequestDto
import javax.inject.Inject

class GroupRemoteDataSource @Inject constructor(
    //private val groupApi: GroupApi
) {

    suspend fun createGroup(request: CreateGroupRequestDto): Result<IdDto> {
//        return try {
//            val response = groupApi.createGroup(request)
//            if (response.isSuccessful) {
//                response.body()?.let { idDto ->
//                    return Result.success(idDto)
//                }
//                Result.failure(Exception("응답은 성공했지만 데이터가 없습니다."))
//            } else {
//                Result.failure(Exception("서버 오류: ${response.code()} - ${response.errorBody()?.string() ?: "알 수 없는 오류"}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(Exception("알 수 없는 오류: ${e.message}", e))
//        }

        return Result.success(IdDto(1))
    }

//    suspend fun fetchGroups(): GroupOverviewPaginationDto {
//        return groupApi.fetchGroups()
//    }
}