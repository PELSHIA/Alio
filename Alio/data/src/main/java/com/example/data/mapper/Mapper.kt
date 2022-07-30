package com.example.data.mapper

import com.example.data.db.room.model.AlarmEntity
import com.example.data.db.room.model.StatsEntity
import com.example.domain.model.Alarm
import com.example.domain.model.Stats

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

fun mapperToAlarm(alarmEntity: AlarmEntity): Alarm {
    return Alarm(
        alarmEntity.id,
        alarmEntity.name,
        alarmEntity.time,
        alarmEntity.dayOfWeek,
        alarmEntity.category,
        alarmEntity.mission,
        alarmEntity.ringtone
    )
}

fun mapperToStatsList(statsList: List<StatsEntity>): List<Stats> {
    return statsList.toList().map {
        Stats(
            it.id,
            it.time
        )
    }
}

fun mapperToStatsEntity(stats: Stats): StatsEntity {
    return StatsEntity(
        stats.id,
        stats.time
    )
}