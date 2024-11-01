package com.aiku.presentation.ui.screen.notification.composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aiku.presentation.state.notification.NotificationViewState

@Composable
fun NotificationContentScreen(
    modifier: Modifier = Modifier,
    notifications: List<NotificationViewState>
) {

    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text(

            )
        }
    }
}