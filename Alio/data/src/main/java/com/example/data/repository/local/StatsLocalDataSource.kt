package com.example.data.repository.local

import com.example.data.db.room.model.AlarmEntity
import com.example.data.db.room.model.StatsEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface StatsLocalDataSource {
    fun allStatsData(): Single<List<StatsEntity>>

    fun insertStats(stats: StatsEntity): Completable

    fun deleteAllStats(): Completable
}