package com.aiku.presentation.ui.screen.signup.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiku.domain.usecase.ReadTermsUseCase
import com.aiku.presentation.ui.screen.login.viewmodel.LoginUiState
import com.aiku.presentation.util.onError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.FileNotFoundException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class TermsViewModel @Inject constructor(
    private val readTermsUseCase: ReadTermsUseCase
) : ViewModel() {

    private val _termsUiState = MutableStateFlow<LoadTermsUiState>(LoadTermsUiState.Idle)
    val termsUiState: StateFlow<LoadTermsUiState> =
        _termsUiState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = LoadTermsUiState.Idle
        )

    fun loadTerms(identifier: Int) {
        readTermsUseCase.execute(identifier)
            .onStart {
                _termsUiState.emit(LoadTermsUiState.Loading)
            }
            .onEach { result ->
                val uiState = when {
                    result.isSuccess -> {
                        val content = result.getOrNull()
                        if (!content.isNullOrEmpty()) {
                            LoadTermsUiState.Success(content)
                        } else {
                            LoadTermsUiState.Failure("파일 내용이 비어 있습니다.")
                        }
                    }
                    result.isFailure -> {
                        val exception = result.exceptionOrNull()
                        LoadTermsUiState.Failure(exception?.message ?: "알 수 없는 오류 발생")
                    }
                    else -> LoadTermsUiState.Failure("알 수 없는 오류 발생")
                }
                _termsUiState.emit(uiState)
            }
            .catch { e ->
                val errorMessage = when (e) {
                    is FileNotFoundException -> "파일을 찾을 수 없습니다."
                    is IOException -> "입출력 오류 발생"
                    else -> "알 수 없는 오류 발생"
                }
                _termsUiState.emit(LoadTermsUiState.Failure(errorMessage))
            }
            .launchIn(viewModelScope)
    }


}

sealed interface LoadTermsUiState {
    data object Idle : LoadTermsUiState
    data object Loading : LoadTermsUiState
    data class Success(val content: String) : LoadTermsUiState
    data class Failure(val message: String) : LoadTermsUiState
}

