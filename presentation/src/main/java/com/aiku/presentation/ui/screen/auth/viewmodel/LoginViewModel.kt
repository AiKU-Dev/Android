package com.aiku.presentation.ui.screen.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiku.domain.delegate.UserDelegate
import com.aiku.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val userDelegate: UserDelegate
) : ViewModel() {

    //private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    //override val uiState: StateFlow<UiState> = _uiState

    //TODO - 현재 : 카카오에서 사용자정보를 성공적으로 받아오면 로그인 성공으로 판단
    // -> idToken검증 성공 : UiState.Success, idToken검증 실패 : UiState.Error(exception)

    fun login(useKakaoTalk: Boolean) {
        viewModelScope.launch {
            loginUseCase.execute(useKakaoTalk)
                .onStart {
                 //   _uiState.value = UiState.Loading
                }
                .catch { exception ->
                //    _uiState.value = UiState.Error(exception)
                }
                .collect { loginResult ->
                    if (loginResult.user != null) {
                        // 사용자 정보 저장
                        userDelegate.saveUser(loginResult.user!!)
                   //     _uiState.value = UiState.Success
                    } else {
                   //     _uiState.value = UiState.Error(loginResult.error ?: Exception("Unknown error"))
                    }
                }
        }
    }
}
