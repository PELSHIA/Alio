package com.example.data.db.room.dao

import androidx.room.*
import com.example.data.db.room.entity.Alarm
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarm_db")
    fun allAlarmList(): Single<List<Alarm>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlarm(alarm: Alarm): Completable

    @Delete
    fun deleteAlarm(alarm: Alarm): Completable

    @Update
    fun updateAlarm(alarm: Alarm): Completable

}