package com.aiku.presentation.ui.group.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.aiku.domain.model.group.Group
import com.aiku.domain.repository.GroupRepository
import com.aiku.presentation.state.group.GroupState
import com.aiku.presentation.state.group.toGroupState
import com.aiku.presentation.util.onError
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel(assistedFactory = GroupViewModel.Factory::class)
class GroupViewModel @AssistedInject constructor(
    @Assisted("groupId") private val groupId: Long,
    private val groupRepository: GroupRepository
) : ViewModel() {

    val group: StateFlow<GroupUiState> = groupRepository.fetchGroup(groupId)
        .map<Group, GroupUiState> { GroupUiState.Success(it.toGroupState()) }
        .onError { emit(GroupUiState.Error(it.message ?: "")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = GroupUiState.Loading
        )

    fun exitGroup() {
        groupRepository.exitGroup(groupId).launchIn(viewModelScope)
    }


    interface Factory {
        fun create(groupId: Long): GroupViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            groupId: Long
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(groupId) as T
                }
            }
        }
    }
}

sealed interface GroupUiState {
    data object Loading : GroupUiState
    data class Success(val group: GroupState) : GroupUiState
    data class Error(val message: String) : GroupUiState
}