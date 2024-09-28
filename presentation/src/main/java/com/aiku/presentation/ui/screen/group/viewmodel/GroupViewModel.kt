package com.aiku.presentation.ui.screen.group.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiku.domain.exception.INVALID_INPUT
import com.aiku.domain.usecase.CreateGroupUseCase
import com.aiku.presentation.util.onError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val createGroupUseCase: CreateGroupUseCase
): ViewModel(){

    // 그룹 이름의 최대 길이 상수
    companion object {
        const val MAX_GROUPNAME_LENGTH = CreateGroupUseCase.MAX_GROUPNAME_LENGTH
    }

    private val _groupNameInput = MutableStateFlow("")
    val groupNameInput: StateFlow<String> = _groupNameInput.asStateFlow()

    private val _isBtnEnabled = MutableStateFlow(false)
    val isBtnEnabled: StateFlow<Boolean> = _isBtnEnabled.asStateFlow()

    private val _createGroupUiState = MutableStateFlow<CreateGroupUiState>(CreateGroupUiState.Idle)
    val createGroupUiState: StateFlow<CreateGroupUiState> = _createGroupUiState

    fun onGroupNameInputChanged(newName: String) {
        _groupNameInput.value = newName
        _isBtnEnabled.value = newName.isNotEmpty()
    }

    fun createGroup() {
        createGroupUseCase.execute(_groupNameInput.value)
            .onStart { _createGroupUiState.emit(CreateGroupUiState.Loading) }
            .onEach { _createGroupUiState.emit(CreateGroupUiState.Success)}
            .onError { error ->
                val uiState = when (error.code) {
                    INVALID_INPUT -> CreateGroupUiState.InvalidInput
                    else -> CreateGroupUiState.ServerError
                }
                _createGroupUiState.emit(uiState)
            }
            .launchIn(viewModelScope)
    }
}

sealed interface CreateGroupUiState {
    data object Idle : CreateGroupUiState
    data object Loading : CreateGroupUiState
    data object Success : CreateGroupUiState
    data object InvalidInput : CreateGroupUiState
    data object ServerError : CreateGroupUiState
}