package com.project.alio.module

import com.example.domain.repository.AlarmRepository
import com.example.domain.useCase.DeleteAlarmUseCase
import com.example.domain.useCase.GetAllAlarmListUseCase
import com.example.domain.useCase.InsertAlarmUseCase
import com.example.domain.useCase.UpdateAlarmUseCase
import com.project.alio.viewModel.AlarmViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllAlarmUseCase(repository: AlarmRepository): GetAllAlarmListUseCase {
        return GetAllAlarmListUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideInsertAlarmUseCase(repository: AlarmRepository): InsertAlarmUseCase {
        return InsertAlarmUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteAlarmUseCase(repository: AlarmRepository): DeleteAlarmUseCase {
        return DeleteAlarmUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideUpdateAlarmUseCase(repository: AlarmRepository): UpdateAlarmUseCase {
        return UpdateAlarmUseCase(repository)
    }
}