package com.example.domain.useCase

import com.example.domain.model.Alarm
import com.example.domain.repository.AlarmRepository
import io.reactivex.rxjava3.core.Completable

class InsertAlarmUseCase(private val repository: AlarmRepository) {
    fun execute(alarm: Alarm): Completable {
        return repository.insertAlarm(alarm)
    }
}