package com.example.domain.repository

import com.example.domain.model.Alarm
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AlarmRepository {
    fun allAlarmList(): Single<List<Alarm>>

    fun insertAlarm(alarm: Alarm): Completable

    fun deleteAlarm(alarm: Alarm): Completable

    fun updateAlarm(alarm: Alarm): Completable
}