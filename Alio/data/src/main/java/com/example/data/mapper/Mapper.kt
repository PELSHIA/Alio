package com.example.data.mapper

import com.example.data.db.room.model.AlarmEntity
import com.example.domain.model.Alarm

fun mapperToAlarmList(alarmList: List<AlarmEntity>): List<Alarm> {
    return alarmList.toList().map {
        Alarm(
            it.id,
            it.name,
            it.time,
            it.dayOfWeek,
            it.category,
            it.mission,
            it.ringtone
        )
    }
}

fun mapperToAlarmEntity(alarm: Alarm): AlarmEntity {
    return AlarmEntity(
        alarm.id,
        alarm.name,
        alarm.time,
        alarm.dayOfWeek,
        alarm.category,
        alarm.mission,
        alarm.ringtone
    )
}