package com.example.data.repository.local

import com.example.data.db.room.model.AlarmEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AlarmLocalDataSource {
    fun allAlarmList(): Single<List<AlarmEntity>>

    fun insertAlarm(alarm: AlarmEntity): Single<Long>

    fun deleteAlarm(alarm: AlarmEntity): Completable

    fun updateAlarm(alarm: AlarmEntity): Completable

    fun selectAlarm(id: Int): Single<AlarmEntity>
}