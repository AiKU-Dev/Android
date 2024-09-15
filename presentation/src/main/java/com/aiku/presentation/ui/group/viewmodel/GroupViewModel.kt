package com.aiku.presentation.ui.group.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiku.domain.model.group.Group
import com.aiku.domain.repository.GroupRepository
import com.aiku.domain.usecase.ExitGroupUseCase
import com.aiku.presentation.state.group.GroupState
import com.aiku.presentation.state.group.toGroupState
import com.aiku.presentation.util.onError
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn

@HiltViewModel(assistedFactory = GroupViewModel.Factory::class)
class GroupViewModel @AssistedInject constructor(
    @Assisted("groupId") private val groupId: Long,
    private val groupRepository: GroupRepository,
    private val exitGroupUseCase: ExitGroupUseCase
) : ViewModel() {

    val groupUiState: StateFlow<GroupUiState> = groupRepository.fetchGroup(groupId)
        .map<Group, GroupUiState> { GroupUiState.Success(it.toGroupState()) }
        .onError { emit(GroupUiState.Error(it.message)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = GroupUiState.Loading
        )

    private val _exitGroupUiState = MutableSharedFlow<ExitGroupUiState>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val exitGroupUiState = _exitGroupUiState.asSharedFlow()

    fun exitGroup(groupState: GroupState) {
        exitGroupUseCase(groupState.toGroup()).onStart {
            _exitGroupUiState.emit(ExitGroupUiState.Loading)
        }.onEach {
            _exitGroupUiState.emit(ExitGroupUiState.Success)
        }.onError {
            TODO("에러 분기")
        }.launchIn(viewModelScope)
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("groupId") groupId: Long): GroupViewModel
    }
}

sealed interface GroupUiState {
    data object Loading : GroupUiState
    data class Success(val group: GroupState) : GroupUiState
    data class Error(val message: String) : GroupUiState
}

sealed interface ExitGroupUiState {
    data object Loading : ExitGroupUiState
    data object RunningScheduleExist : ExitGroupUiState
    data object Success : ExitGroupUiState
}