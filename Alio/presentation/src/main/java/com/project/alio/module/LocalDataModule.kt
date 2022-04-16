package com.project.alio.module

import com.example.data.db.room.dao.AlarmDao
import com.example.data.repository.local.AlarmLocalDataSource
import com.example.data.repository.local.AlarmLocalSourceDataImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Singleton
    @Provides
    fun provideAlarmLocalData(alarmDao: AlarmDao): AlarmLocalDataSource {
        return AlarmLocalSourceDataImpl(alarmDao)
    }
}