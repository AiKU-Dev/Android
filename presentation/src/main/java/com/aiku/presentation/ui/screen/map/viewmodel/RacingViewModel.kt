package com.aiku.presentation.ui.screen.map.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RacingViewModel @Inject constructor(
) : ViewModel() {

    /** 현재 아쿠 **/
    private val _currentAku = MutableStateFlow(10) // 기본 아쿠를 10으로 설정
    val currentAku: StateFlow<Int> = _currentAku.asStateFlow()

    /** 현재 아쿠 -10 **/
    fun decreaseAku() {
        viewModelScope.launch {
            _currentAku.value = (_currentAku.value - 10).coerceAtLeast(10) // 최소값 10으로 제한
        }
    }

    /** 현재 아쿠 +10 **/
    fun increaseAku() {
        viewModelScope.launch {
            _currentAku.value = _currentAku.value + 10
        }
    }
}