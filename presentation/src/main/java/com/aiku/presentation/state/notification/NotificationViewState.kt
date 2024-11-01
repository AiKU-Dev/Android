package com.aiku.presentation.state.notification

import com.aiku.domain.model.notification.Notification
import com.aiku.domain.model.notification.NotificationCategory

data class NotificationViewState(
    val category: NotificationCategory,
    val title: String,
    val content: String,
) {

    fun toNotification() = Notification(
        category = category,
        title = title,
        content = content,
    )
}

fun Notification.toNotificationViewState() = NotificationViewState(
    category = category,
    title = title,
    content = content,
)