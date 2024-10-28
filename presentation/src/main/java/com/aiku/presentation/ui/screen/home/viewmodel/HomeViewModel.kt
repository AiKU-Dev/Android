package com.aiku.presentation.ui.screen.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiku.domain.usecase.FetchUserSchedulesUseCase
import com.aiku.presentation.state.schedule.UserScheduleOverviewPaginationState
import com.aiku.presentation.state.schedule.toUserScheduleOverviewPaginationState
import com.aiku.presentation.util.onError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import java.time.LocalDateTime
import javax.inject.Inject

// 오늘 내 약속, 내 그룹
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val fetchUserSchedulesUseCase: FetchUserSchedulesUseCase
) : ViewModel() {

    private val _userSchedulesPage = savedStateHandle.getStateFlow(PAGE_OF_SCHEDULES, 1)

    private val _userScheduleUiState = MutableStateFlow<UserScheduleUiState>(UserScheduleUiState.Loading)
    val userScheduleUiState: StateFlow<UserScheduleUiState> = _userScheduleUiState.asStateFlow()

    init {
        fetchUserSchedules()
    }

    fun onUserSchedulePageChanged(page: Int) {
        savedStateHandle[PAGE_OF_SCHEDULES] = page
        fetchUserSchedules()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun fetchUserSchedules() {
        val startDate = LocalDateTime.now()
        val endDate = LocalDateTime.now()
        _userSchedulesPage
            .flatMapLatest { page -> fetchUserSchedulesUseCase.execute(page, startDate, endDate) }
            .onStart { _userScheduleUiState.emit(UserScheduleUiState.Loading) }
            .onEach { _userScheduleUiState.emit(UserScheduleUiState.Success(it.toUserScheduleOverviewPaginationState())) }
            .onError { /* error */}
            .launchIn(viewModelScope)
    }

    companion object {
        private const val PAGE_OF_SCHEDULES = "page_of_schedules"
    }
}

sealed interface UserScheduleUiState {
    data object Loading : UserScheduleUiState
    data class Success(val scheduleOverviewPagination: UserScheduleOverviewPaginationState) : UserScheduleUiState
}
