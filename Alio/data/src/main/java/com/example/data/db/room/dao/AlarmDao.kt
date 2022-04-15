package com.example.data.db.room.dao

import androidx.room.*
import com.example.data.db.room.model.AlarmEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarm_db")
    fun allAlarmList(): Single<List<AlarmEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlarm(alarm: AlarmEntity): Completable

    @Delete
    fun deleteAlarm(alarm: AlarmEntity): Completable

    @Update
    fun updateAlarm(alarm: AlarmEntity): Completable

}