package com.project.alio.module

import com.example.data.repository.AlarmRepositoryImpl
import com.example.data.repository.StatsRepositoryImpl
import com.example.data.repository.local.AlarmLocalDataSource
import com.example.data.repository.local.StatsLocalDataSource
import com.example.domain.repository.AlarmRepository
import com.example.domain.repository.StatsRepository
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

    @Singleton
    @Provides
    fun provideStatsRepository(statsLocalDataSource: StatsLocalDataSource): StatsRepository {
        return StatsRepositoryImpl(statsLocalDataSource)
    }
}