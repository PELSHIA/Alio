package com.example.data.db.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.db.room.converter.ListTypeConverter
import com.example.data.db.room.converter.CalendarTypeConverter
import com.example.data.db.room.converter.RingtoneTypeConverter
import com.example.data.db.room.dao.AlarmDao
import com.example.data.db.room.model.AlarmEntity
import com.google.gson.Gson

@Database(entities = [AlarmEntity::class], version = 1)
@TypeConverters(
    value = [
        RingtoneTypeConverter::class,
        CalendarTypeConverter::class,
        ListTypeConverter::class
    ]
)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao

    companion object {
        private var instance: AlarmDatabase? = null

        fun getInstance(context: Context): AlarmDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlarmDatabase::class.java,
                    "alarm_table"
                )
                    .addTypeConverter(RingtoneTypeConverter(Gson()))
                    .addTypeConverter(CalendarTypeConverter(Gson()))
                    .addTypeConverter(ListTypeConverter(Gson()))
                    .build()
            }
            return instance
        }
    }
}