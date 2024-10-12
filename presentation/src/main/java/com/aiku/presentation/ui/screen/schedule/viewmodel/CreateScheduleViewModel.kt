package com.aiku.presentation.ui.screen.schedule.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiku.domain.repository.CreateScheduleRepository
import com.aiku.domain.usecase.CreateScheduleUseCase
import com.aiku.presentation.state.schedule.LocationState
import com.aiku.presentation.state.schedule.toLocationState
import com.aiku.presentation.util.onError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateScheduleViewModel @Inject constructor(
    private val createScheduleUseCase: CreateScheduleUseCase
): ViewModel(){

    private val _locationResults = MutableStateFlow<List<LocationState>>(emptyList())
    val locationResults: StateFlow<List<LocationState>> = _locationResults.asStateFlow()


    private val _selectedLocation = MutableStateFlow<LocationState?>(null)
    val selectedLocation: StateFlow<LocationState?> = _selectedLocation

    // 장소 검색
//    fun searchLocations(query: String) {
//        viewModelScope.launch {
//            createScheduleUseCase.searchLocations(query).collect { results ->
//                _locationResults.value = results
//            }
//        }
//    }

    // 마커 이동 시 선택된 장소 업데이트
    fun updateLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            createScheduleUseCase.getLocation(latitude, longitude)
                .onStart {
                }
                .onEach { location ->
                    _selectedLocation.value = location.toLocationState()
                }
                .onError { error ->
                    _selectedLocation.value = null
                }
                .launchIn(this)
        }
    }
}