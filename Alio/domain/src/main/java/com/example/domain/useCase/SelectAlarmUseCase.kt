package com.example.domain.useCase

import com.example.domain.model.Alarm
import com.example.domain.repository.AlarmRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class SelectAlarmUseCase(private val repository: AlarmRepository) {
    fun execute(id: Int): Single<Alarm> {
        return repository.selectAlarm(id)
    }
}