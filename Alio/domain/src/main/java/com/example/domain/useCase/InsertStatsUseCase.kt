package com.example.domain.useCase

import com.example.domain.model.Alarm
import com.example.domain.model.Stats
import com.example.domain.repository.AlarmRepository
import com.example.domain.repository.StatsRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class InsertStatsUseCase(private val repository: StatsRepository) {
    fun execute(stats: Stats): Completable {
        return repository.insertStats(stats)
    }
}