package com.example.data.repository

import com.example.data.mapper.*
import com.example.data.repository.local.AlarmLocalDataSource
import com.example.data.repository.local.StatsLocalDataSource
import com.example.domain.model.Alarm
import com.example.domain.model.Stats
import com.example.domain.repository.AlarmRepository
import com.example.domain.repository.StatsRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class StatsRepositoryImpl(private val statsLocalSource: StatsLocalDataSource): StatsRepository {
    override fun allStatsList(): Single<List<Stats>> {
        return statsLocalSource.allStatsData().flatMap {
            Single.just(mapperToStatsList(it))
        }
    }

    override fun insertStats(stats: Stats): Completable {
        return statsLocalSource.insertStats(mapperToStatsEntity(stats))
    }

    override fun deleteAllStats(): Completable {
        return statsLocalSource.deleteAllStats()
    }
}