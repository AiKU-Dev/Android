package com.aiku.presentation.ui.screen.schedule.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiku.domain.usecase.CreateScheduleUseCase
import com.aiku.presentation.state.schedule.LocationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val createScheduleUseCase : CreateScheduleUseCase
): ViewModel(){

    // 약속 이름의 최대 길이 상수
    companion object {
        const val MAX_SCHEDULENAME_LENGTH = CreateScheduleUseCase.MAX_SCHEDULENAME_LENGTH
    }

    //TODO : ScheduleDto에 저장
    private val _scheduleNameInput = MutableStateFlow("")
    val scheduleNameInput: StateFlow<String> = _scheduleNameInput.asStateFlow()

    private val _scheduleDateInput = MutableStateFlow("")
    val scheduleDateInput: StateFlow<String> = _scheduleDateInput.asStateFlow()

    private val _scheduleLocationInput = MutableStateFlow("")
    val scheduleLocationInput: StateFlow<String> = _scheduleLocationInput.asStateFlow()

    //TODO : 이름 변경
    private val _isBtnEnabled = MutableStateFlow(false)
    val isBtnEnabled: StateFlow<Boolean> = _isBtnEnabled.asStateFlow()

    fun onScheduleNameInputChanged(newName: String) {
        _scheduleNameInput.value = newName
        _isBtnEnabled.value = newName.isNotEmpty()
    }
}