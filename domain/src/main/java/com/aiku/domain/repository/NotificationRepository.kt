package com.aiku.domain.repository

import com.aiku.domain.model.notification.Notification

interface NotificationRepository {

    fun insertNotification(notification: Notification)
}