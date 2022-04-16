package com.project.alio.module

import com.example.data.repository.AlarmRepositoryImpl
import com.example.data.repository.local.AlarmLocalDataSource
import com.example.domain.repository.AlarmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAlarmRepository(alarmLocalSource: AlarmLocalDataSource): AlarmRepository {
        return AlarmRepositoryImpl(alarmLocalSource)
    }
}