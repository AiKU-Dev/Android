package com.aiku.presentation.ui.screen.auth.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.aiku.core.base.BaseViewModel
import com.aiku.core.base.UiState
import com.aiku.domain.delegate.UserDelegate
import com.aiku.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val userDelegate: UserDelegate
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    override val uiState: StateFlow<UiState> = _uiState

    fun login(useKakaoTalk: Boolean) {
        viewModelScope.launch {
            loginUseCase.execute(useKakaoTalk)
                .onStart {
                    _uiState.value = UiState.Loading
                }
                .catch { exception ->
                    _uiState.value = UiState.Error(exception)
                }
                .collect { loginResult ->
                    if (loginResult.user != null) {
                        // 사용자 정보 저장
                        userDelegate.saveUser(loginResult.user!!)
                        _uiState.value = UiState.Success
                    } else {
                        _uiState.value = UiState.Error(loginResult.error ?: Exception("Unknown error"))
                    }
                }
        }
    }
}
