package com.aiku.presentation.ui.screen.notification.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiku.domain.model.notification.Notification
import com.aiku.domain.repository.NotificationRepository
import com.aiku.presentation.state.notification.NotificationViewState
import com.aiku.presentation.state.notification.toNotificationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
): ViewModel() {

    val notificationUiState = notificationRepository
        .getNotifications()
        .map<List<Notification>, NotificationUiState> {
            if (it.isEmpty()) {
                NotificationUiState.Empty
            } else {
                NotificationUiState.Success(it.map { notification -> notification.toNotificationViewState() })
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NotificationUiState.Loading
        )
}

sealed interface NotificationUiState {
    data object Loading: NotificationUiState
    data class Success(val notifications: List<NotificationViewState>): NotificationUiState
    data object Empty: NotificationUiState
}