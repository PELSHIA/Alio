package com.example.data.repository

import com.example.data.mapper.mapperToAlarmEntity
import com.example.data.mapper.mapperToAlarmList
import com.example.data.repository.local.AlarmLocalDataSource
import com.example.domain.model.Alarm
import com.example.domain.repository.AlarmRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class AlarmRepositoryImpl(private val alarmLocalSource: AlarmLocalDataSource): AlarmRepository {
    override fun allAlarmList(): Single<List<Alarm>> {
        return alarmLocalSource.allAlarmList().flatMap {
            Single.just(mapperToAlarmList(it))
        }
    }

    override fun insertAlarm(alarm: Alarm): Single<Long> {
        return alarmLocalSource.insertAlarm(mapperToAlarmEntity(alarm))
    }

    override fun deleteAlarm(alarm: Alarm): Completable {
        return alarmLocalSource.deleteAlarm(mapperToAlarmEntity(alarm))
    }

    override fun updateAlarm(alarm: Alarm): Completable {
        return alarmLocalSource.updateAlarm(mapperToAlarmEntity(alarm))
    }
}