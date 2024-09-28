package com.aiku.domain.usecase


import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.INVALID_INPUT
import com.aiku.domain.model.group.Group
import com.aiku.domain.repository.GroupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateGroupUseCase @Inject constructor(
    private val groupRepository: GroupRepository
) {
    companion object {
        const val MAX_GROUPNAME_LENGTH = 15
    }

    fun execute(groupName: String): Flow<Result<Group>> {
        return flow {
            if (groupName.length > MAX_GROUPNAME_LENGTH) {
                throw ErrorResponse(INVALID_INPUT, "그룹 이름이 너무 깁니다.")
            }
            groupRepository.createGroup(groupName).collect { result ->
                emit(result)
            }
        }
    }
}