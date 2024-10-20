package com.aiku.presentation.ui.group.schedule.waiting.viewmodel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BettingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val akuInput = savedStateHandle.getStateFlow(AKU, "")

    fun onBetAkuChanged(betAku: String) {
        if (betAku.isDigitsOnly()) {
            savedStateHandle[AKU] = (betAku.toIntOrNull() ?: "").toString()
        }
    }

    companion object {
        const val AKU = "aku"
    }
}