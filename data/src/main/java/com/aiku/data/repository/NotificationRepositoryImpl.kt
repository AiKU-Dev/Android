package com.aiku.data.repository

import com.aiku.data.entity.toNotificationEntity
import com.aiku.data.source.local.NotificationLocalDataSource
import com.aiku.domain.model.notification.Notification
import com.aiku.domain.repository.NotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationLocalDataSource: NotificationLocalDataSource
) : NotificationRepository {

    override fun insertNotification(notification: Notification) {
        notificationLocalDataSource.insertNotification(notification.toNotificationEntity())
    }
}