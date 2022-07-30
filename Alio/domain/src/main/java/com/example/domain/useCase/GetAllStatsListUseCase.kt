package com.example.domain.useCase

import com.example.domain.model.Stats
import com.example.domain.repository.StatsRepository
import io.reactivex.rxjava3.core.Single

class GetAllStatsListUseCase(private val repository: StatsRepository) {
    fun execute() : Single<List<Stats>> {
        return repository.allStatsList()
    }
}