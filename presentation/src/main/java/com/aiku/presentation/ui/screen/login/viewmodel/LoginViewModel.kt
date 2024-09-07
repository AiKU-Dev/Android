package com.aiku.presentation.ui.screen.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiku.domain.exception.INVALID_ID_TOKEN
import com.aiku.domain.exception.USER_NOT_FOUND
import com.aiku.domain.usecase.LoginUseCase
import com.aiku.presentation.util.onError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginUiState: StateFlow<LoginUiState> =
        _loginUiState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = LoginUiState.Idle
        )


    fun login(useKakaoTalk: Boolean) {
        viewModelScope.launch {
            loginUseCase.execute(useKakaoTalk)
                .onStart {
                    _loginUiState.emit(LoginUiState.Loading)
                }
                .onEach { token ->
                    _loginUiState.emit(LoginUiState.Success)
                }
                .onError { error ->
                    val uiState = when (error.code) {
                        USER_NOT_FOUND -> LoginUiState.UserNotFound
                        INVALID_ID_TOKEN -> LoginUiState.InvalidIdToken
                        else -> LoginUiState.Idle
                    }
                    _loginUiState.emit(uiState)
                }
                .launchIn(this) // Use 'this' to specify the current coroutine scope
        }
    }

}

sealed interface LoginUiState {
    data object Idle : LoginUiState
    data object Loading : LoginUiState
    data object Success : LoginUiState
    data object InvalidIdToken : LoginUiState
    data object UserNotFound : LoginUiState
}