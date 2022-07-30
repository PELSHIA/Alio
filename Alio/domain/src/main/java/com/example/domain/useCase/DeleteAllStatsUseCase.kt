package com.example.domain.useCase

import com.example.domain.repository.StatsRepository
import io.reactivex.rxjava3.core.Completable

class DeleteAllStatsUseCase(private val repository: StatsRepository) {
    fun execute(): Completable {
        return repository.deleteAllStats()
    }
}