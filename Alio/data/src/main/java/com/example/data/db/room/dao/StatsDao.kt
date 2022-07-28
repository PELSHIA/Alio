package com.example.data.db.room.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.data.db.room.model.StatsEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface StatsDao {
    @Query("SELECT * FROM stats_db")
    fun allStatsData(): Single<List<StatsEntity>>

    @Insert
    fun insertStats(stats: StatsEntity): Completable

    @Query("DELETE FROM alarm_db")
    fun deleteAllStats(): Completable
}