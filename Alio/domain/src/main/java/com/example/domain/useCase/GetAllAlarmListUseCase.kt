package com.example.domain.useCase

import com.example.domain.model.Alarm
import com.example.domain.repository.AlarmRepository
import io.reactivex.rxjava3.core.Single

class GetAllAlarmListUseCase(private val repository: AlarmRepository) {
    fun execute(): Single<List<Alarm>> {
        return repository.allAlarmList()
    }
}