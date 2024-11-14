package com.aiku.presentation.ui.screen.signup.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.aiku.domain.exception.ErrorResponse
import com.aiku.domain.exception.NICKNAME_LENGTH_EXCEED
import com.aiku.domain.exception.REQUIRE_NICKNAME_INPUT
import com.aiku.domain.repository.UserRepository
import com.aiku.domain.usecase.CheckNicknameExistUseCase
import com.aiku.domain.usecase.SaveUserUseCase
import com.aiku.presentation.navigation.route.Routes
import com.aiku.presentation.state.user.NewUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProfileViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val saveUserUseCase: SaveUserUseCase,
    private val checkNicknameExistUseCase: CheckNicknameExistUseCase,
    private val userRepository: UserRepository
): ViewModel() {

    private val arguments = savedStateHandle.toRoute<Routes.Auth.ProfileEdit>()

    val profileInput = savedStateHandle.getStateFlow(PROFILE_INPUT, NewUserState.EMPTY.copy(
        email = arguments.email ?: "",
        idToken = arguments.idToken,
    ))

    private val _saveProfileUiState = MutableSharedFlow<SaveProfileUiState>()
    val saveProfileUiState = _saveProfileUiState.asSharedFlow()

    private val _checkNicknameValidUiState = MutableSharedFlow<CheckNicknameValidUiState>(extraBufferCapacity = 1)
    val checkNicknameValidUiState = _checkNicknameValidUiState.asSharedFlow()

    fun saveProfile() {
        viewModelScope.launch {
            saveUserUseCase(profileInput.value.toNewUser()).onSuccess {
                _saveProfileUiState.emit(SaveProfileUiState.Success)
            }.onFailure {
                when ((it as ErrorResponse).code) {
                    else -> _saveProfileUiState.emit(SaveProfileUiState.Failed)
                }
            }
        }
    }

    fun onNicknameInputChanged(input: String) {
        _checkNicknameValidUiState.tryEmit(CheckNicknameValidUiState.Waiting)
        onProfileInputChanged(profileInput.value.copy(nickname = input))
    }

    fun onImageInputChanged(input: String) {
        onProfileInputChanged(profileInput.value.copy(profile = profileInput.value.profile.copy(image = input)))
    }

    fun onEmailInputChanged(input: String) {
        onProfileInputChanged(profileInput.value.copy(email = input))
    }

    fun onRecommenderNicknameInputChanged(input: String) {
        onProfileInputChanged(profileInput.value.copy(recommenderNickname = input))
    }

    fun checkIsNicknameExist() {
        viewModelScope.launch {
            checkNicknameExistUseCase(profileInput.value.nickname).onSuccess {
                _checkNicknameValidUiState.emit(CheckNicknameValidUiState.Success)
            }.onFailure {
                when ((it as ErrorResponse).code) {
                    REQUIRE_NICKNAME_INPUT -> _checkNicknameValidUiState.emit(CheckNicknameValidUiState.Empty)
                    NICKNAME_LENGTH_EXCEED -> _checkNicknameValidUiState.emit(CheckNicknameValidUiState.LengthExceed)
                    else -> _checkNicknameValidUiState.emit(CheckNicknameValidUiState.AlreadyExist)
                }
            }
        }
    }

    private fun onProfileInputChanged(user: NewUserState) {
        savedStateHandle[PROFILE_INPUT] = user
    }

    companion object {
        const val MAX_NICKNAME_LENGTH = SaveUserUseCase.MAX_NICKNAME_LENGTH
        private const val PROFILE_INPUT = "profile_input"
    }
}

sealed interface SaveProfileUiState {
    data object Loading : SaveProfileUiState
    data object Success : SaveProfileUiState
    data object Failed : SaveProfileUiState
}

sealed interface CheckNicknameValidUiState {
    data object Waiting : CheckNicknameValidUiState
    data object Success : CheckNicknameValidUiState
    data object Empty : CheckNicknameValidUiState
    data object LengthExceed : CheckNicknameValidUiState
    data object AlreadyExist : CheckNicknameValidUiState
}