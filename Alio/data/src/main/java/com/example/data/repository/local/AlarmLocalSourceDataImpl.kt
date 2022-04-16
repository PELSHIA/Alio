package com.example.data.repository.local

import com.example.data.db.room.dao.AlarmDao
import com.example.data.db.room.model.AlarmEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class AlarmLocalSourceDataImpl(private val alarmDao: AlarmDao): AlarmLocalDataSource {
    override fun allAlarmList(): Single<List<AlarmEntity>> {
        return alarmDao.allAlarmList()
    }

    override fun insertAlarm(alarm: AlarmEntity): Completable {
        return alarmDao.insertAlarm(alarm)
    }

    override fun deleteAlarm(alarm: AlarmEntity): Completable {
        return alarmDao.deleteAlarm(alarm)
    }

    override fun updateAlarm(alarm: AlarmEntity): Completable {
        return alarmDao.updateAlarm(alarm)
    }
}