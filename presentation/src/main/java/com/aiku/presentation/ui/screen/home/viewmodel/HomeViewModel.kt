package com.aiku.presentation.ui.screen.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.aiku.domain.usecase.group.FetchGroupsUseCase
import com.aiku.domain.usecase.schedule.FetchUserSchedulesUseCase
import com.aiku.presentation.state.group.GroupOverviewState
import com.aiku.presentation.state.group.toGroupOverviewState
import com.aiku.presentation.state.schedule.UserScheduleOverviewState
import com.aiku.presentation.state.schedule.toUserScheduleOverviewState
import com.aiku.presentation.util.onError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import java.time.LocalDateTime
import javax.inject.Inject

// 오늘 내 약속, 내 그룹
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchUserSchedulesUseCase: FetchUserSchedulesUseCase,
    private val fetchGroupsUseCase: FetchGroupsUseCase
) : ViewModel() {

    /** 오늘 내 약속 **/
    private val _userSchedulesUiState = MutableStateFlow<UserSchedulesUiState>(UserSchedulesUiState.Loading)
    val userSchedulesUiState: StateFlow<UserSchedulesUiState> = _userSchedulesUiState.asStateFlow()

    private val startDate = LocalDateTime.now()
    private val endDate = LocalDateTime.now()

    val userSchedules: Flow<PagingData<UserScheduleOverviewState>> = fetchUserSchedulesUseCase(startDate, endDate)
        .map { pagingData ->
            pagingData.map { it.toUserScheduleOverviewState() }
        }
        .cachedIn(viewModelScope)
        .onStart { _userSchedulesUiState.emit(UserSchedulesUiState.Loading) }
        .onEach { _userSchedulesUiState.emit(UserSchedulesUiState.Success) }
        .onError { _userSchedulesUiState.emit(UserSchedulesUiState.Error) }

    /** 내 그룹 **/
    private val _groupsUiState = MutableStateFlow<GroupsUiState>(GroupsUiState.Loading)
    val groupsUiState: StateFlow<GroupsUiState> = _groupsUiState.asStateFlow()

    val groups: Flow<PagingData<GroupOverviewState>> = fetchGroupsUseCase()
        .map { pagingData ->
            pagingData.map { it.toGroupOverviewState() }
        }
        .cachedIn(viewModelScope)
        .onStart { _groupsUiState.emit(GroupsUiState.Loading) }
        .onEach { _groupsUiState.emit(GroupsUiState.Success) }
        .onError { _groupsUiState.emit(GroupsUiState.Error) }

}

sealed interface UserSchedulesUiState {
    data object Loading : UserSchedulesUiState
    data object Success : UserSchedulesUiState
    data object Error : UserSchedulesUiState
}

sealed interface GroupsUiState {
    data object Loading : GroupsUiState
    data object Success : GroupsUiState
    data object Error : GroupsUiState
}
