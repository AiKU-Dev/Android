package com.aiku.aiku.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.aiku.aiku.serializer.UserEntitySerializer
import com.aiku.data.UserEntity
import com.aiku.data.api.local.UserDataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.userProtoDataStore: DataStore<UserEntity> by dataStore(fileName = "user.pb", serializer = UserEntitySerializer)

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUserDataStore(
        @ApplicationContext context: Context
    ): DataStore<UserEntity> {
        return context.userProtoDataStore
    }

    @Provides
    @Singleton
    fun provideUserDataStoreManager(
        dataStore: DataStore<UserEntity>
    ): UserDataStoreManager {
        return UserDataStoreManager(dataStore)
    }
}