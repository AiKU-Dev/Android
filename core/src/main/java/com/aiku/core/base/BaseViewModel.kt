package com.aiku.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState>
        get() = _uiState

    protected fun CoroutineScope.launchWithUiState(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = this.launch(
        context, start
    ) {
        _uiState.value = UiState.Loading

        block()
    }
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    userDelegate: UserDelegate
) : BaseViewModel(), UserDelegate by userDelegate {

    private val _login: MutableSharedFlow<LoginResultModel> = MutableSharedFlow()
    val login: SharedFlow<LoginResultModel>
        get() = _login

    init {
        viewModelScope.launchWithUiState {
            user.collect {
                _login.emit(LoginResultModel(it.id.isNotEmpty()))
            }
        }
    }
    fun kakaoLogin(loginKaKaoUseCase: LoginKaKaoUseCase) {
        viewModelScope.launch {
            loginKaKaoUseCase(Unit).collect {
                it.onSuccess{
                    _uiState.value = UiState.Success
                }.onFailure {
                    _uiState.value = UiState.Error
                }
            }
        }
    }

    fun guestLogin(loginGuestUseCase: LoginGuestUseCase) {
        viewModelScope.launch {
            loginGuestUseCase(Unit).collect {
                it.onComplete {
                    _login.emit(LoginResultModel(true))
                }
            }
        }
    }
}