package com.aiku.presentation.ui.screen.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiku.domain.usecase.ReadTermsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TermsViewModel @Inject constructor(private val readTermsUseCase: ReadTermsUseCase) : ViewModel() {

    private val _termsContent = MutableStateFlow<String?>(null)
    val termsContent: StateFlow<String?> = _termsContent

    fun loadTerms(resId: Int) {
        viewModelScope.launch {
            val terms = readTermsUseCase.execute(resId)
            _termsContent.value = terms
        }
    }
}

sealed interface LoadTermsUiState {
    data object Idle : LoadTermsUiState
    data object Loading : LoadTermsUiState
    data object Success : LoadTermsUiState
    data object Failure : LoadTermsUiState
}
