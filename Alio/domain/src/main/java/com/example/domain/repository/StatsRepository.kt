package com.example.domain.repository

import com.example.domain.model.Alarm
import com.example.domain.model.Stats
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface StatsRepository {
    fun allStatsList(): Single<List<Stats>>

    fun insertStats(stats: Stats): Completable

    fun deleteAllStats(): Completable
}