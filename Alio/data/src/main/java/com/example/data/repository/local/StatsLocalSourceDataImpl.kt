package com.example.data.repository.local

import com.example.data.db.room.dao.AlarmDao
import com.example.data.db.room.dao.StatsDao
import com.example.data.db.room.model.AlarmEntity
import com.example.data.db.room.model.StatsEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.Callable

class StatsLocalSourceDataImpl(private val statsDao: StatsDao): StatsLocalDataSource {
    override fun allStatsData(): Single<List<StatsEntity>> {
        return statsDao.allStatsData()
    }

    override fun insertStats(stats: StatsEntity): Completable {
        return statsDao.insertStats(stats)
    }

    override fun deleteAllStats(): Completable {
        return statsDao.deleteAllStats()
    }

}