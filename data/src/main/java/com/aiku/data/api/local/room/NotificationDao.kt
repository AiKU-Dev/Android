package com.aiku.data.api.local.room

import androidx.room.Dao
import androidx.room.Insert
import com.aiku.data.entity.NotificationEntity

@Dao
interface NotificationDao {

    @Insert
    fun insertNotification(notification: NotificationEntity)
}