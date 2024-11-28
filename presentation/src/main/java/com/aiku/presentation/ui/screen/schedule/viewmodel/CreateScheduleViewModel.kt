package com.aiku.presentation.ui.screen.schedule.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiku.domain.model.schedule.Location
import com.aiku.domain.model.schedule.Schedule
import com.aiku.domain.usecase.CreateScheduleUseCase
import com.aiku.domain.usecase.schedule.SearchPlacesByKeywordUseCase
import com.aiku.presentation.state.schedule.PlaceState
import com.aiku.presentation.state.schedule.toPlaceState
import com.aiku.presentation.util.onError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class CreateScheduleViewModel @Inject constructor(
    private val createScheduleUseCase: CreateScheduleUseCase,
    private val searchPlacesByKeywordUseCase: SearchPlacesByKeywordUseCase
) : ViewModel() {

    /** 약속 이름 최대 글자 수 */
    companion object {
        const val MAX_SCHEDULE_NAME_LENGTH = CreateScheduleUseCase.MAX_SCHEDULENAME_LENGTH
    }

    /** 약속 이름 */
    private val _scheduleNameInput = MutableStateFlow("")
    val scheduleNameInput: StateFlow<String> = _scheduleNameInput.asStateFlow()

    /** 약속 시간 */
    private val _scheduleDateInput = MutableStateFlow<LocalDate?>(null)
    val scheduleDateInput: StateFlow<LocalDate?> = _scheduleDateInput.asStateFlow()

    private val _scheduleTimeInput = MutableStateFlow<LocalTime?>(null)
    val scheduleTimeInput: StateFlow<LocalTime?> = _scheduleTimeInput.asStateFlow()

    private val scheduleDateTime: StateFlow<LocalDateTime?> = combine(
        _scheduleDateInput,
        _scheduleTimeInput
    ) { date, time ->
        if (date != null && time != null) {
            LocalDateTime.of(date, time)
        } else {
            null
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    /** 약속 장소 */
    private val _scheduleLocation = MutableStateFlow<PlaceState?>(null)
    val scheduleLocation: StateFlow<PlaceState?> = _scheduleLocation.asStateFlow()

    /** 약속 생성 버튼 */
    private val _isBtnEnabled = MutableStateFlow(false)
    val isBtnEnabled: StateFlow<Boolean> = _isBtnEnabled.asStateFlow()

    init {
        combine(
            _scheduleNameInput,
            scheduleDateTime,
            _scheduleLocation
        ) { name, dateTime, location ->
            name.isNotBlank() && dateTime != null && location != null
        }.onEach { isEnabled ->
            _isBtnEnabled.value = isEnabled
        }.launchIn(viewModelScope)
    }

    private val _createScheduleUiState =
        MutableStateFlow<CreateScheduleUiState>(CreateScheduleUiState.Idle)
    val createScheduleUiState: StateFlow<CreateScheduleUiState> = _createScheduleUiState

    fun onScheduleNameInputChanged(newName: String) {
        _scheduleNameInput.value = newName
    }

    fun updateScheduleDate(selectedDate: LocalDate) {
        _scheduleDateInput.value = selectedDate
    }

    fun updateScheduleTime(selectedTime: LocalTime) {
        _scheduleTimeInput.value = selectedTime
    }

    fun updateScheduleLocation(place: PlaceState) {
        _scheduleLocation.value = place
    }

    /** 약속 생성 */
    fun createSchedule(groupId: Long) {
        _scheduleLocation.value?.let {
            scheduleDateTime.value?.let { it1 ->
                createScheduleUseCase(
                    groupId = groupId,
                    scheduleName = _scheduleNameInput.value,
                    location = Location(it.latitude, it.longitude, it.placeName),
                    scheduleTime = it1,
                    pointAmount = 0 //TODO : 디자인 수정중
                )
                    .onStart { _createScheduleUiState.emit(CreateScheduleUiState.Loading) }
                    .onEach { _createScheduleUiState.emit(CreateScheduleUiState.Success) }
                    .onError { _createScheduleUiState.emit(CreateScheduleUiState.Error) }
                    .launchIn(viewModelScope)
            }
        }
    }

    /** 검색된 장소 이름 */
    private val _placeNameInput = MutableStateFlow("")
    val placeNameInput: StateFlow<String> = _placeNameInput.asStateFlow()

    /** 검색된 장소 리스트 */
    private val _searchResults = MutableStateFlow<List<PlaceState>>(emptyList())
    val searchResults: StateFlow<List<PlaceState>> = _searchResults.asStateFlow()

    private val _searchPlacesByKeywordUiState = MutableStateFlow<SearchPlacesByKeywordUiState>(SearchPlacesByKeywordUiState.Loading)
    val searchPlacesByKeywordUiState: StateFlow<SearchPlacesByKeywordUiState> = _searchPlacesByKeywordUiState

    /** 장소 이름 입력 변경 */
    fun onPlaceNameChanged(newName: String) {
        _placeNameInput.value = newName
        if (newName.isNotBlank()) {
            searchPlacesByKeyword(newName)
        }
    }

    /** 키워드로 장소 검색 */
    private fun searchPlacesByKeyword(keyword: String) {
        viewModelScope.launch {
            searchPlacesByKeywordUseCase(keyword)
                .map { places -> places.map { it.toPlaceState() } }
                .onStart { _searchPlacesByKeywordUiState.value = SearchPlacesByKeywordUiState.Loading }
                .onEach { places ->
                    _searchResults.value = places
                    _searchPlacesByKeywordUiState.value = SearchPlacesByKeywordUiState.Success
                }
                .onError {
                    Log.d("kakao rest api", it.message ?: "Unknown error")
                    _searchPlacesByKeywordUiState.value = SearchPlacesByKeywordUiState.Error
                }
                .launchIn(this)
        }
    }
}

sealed interface CreateScheduleUiState {
    data object Idle : CreateScheduleUiState
    data object Loading : CreateScheduleUiState
    data object Success : CreateScheduleUiState
    data object Error : CreateScheduleUiState
}

sealed interface SearchPlacesByKeywordUiState {
    data object Loading : SearchPlacesByKeywordUiState
    data object Success : SearchPlacesByKeywordUiState
    data object Error : SearchPlacesByKeywordUiState
}