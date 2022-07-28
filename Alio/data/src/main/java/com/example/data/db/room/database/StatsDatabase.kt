package com.example.data.db.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.db.room.converter.CalendarTypeConverter
import com.example.data.db.room.converter.ListTypeConverter
import com.example.data.db.room.converter.RingtoneTypeConverter
import com.example.data.db.room.dao.StatsDao
import com.example.data.db.room.model.StatsEntity
import com.google.gson.Gson

@Database(entities = [StatsEntity::class], version = 1)
@TypeConverters(
    value = [ CalendarTypeConverter::class ]
)
abstract class StatsDatabase : RoomDatabase() {
    abstract fun statsDao(): StatsDao

    companion object {
        private var instance: StatsDatabase? = null

        fun getInstance(context: Context): StatsDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    StatsDatabase::class.java,
                    "stats_table"
                )
                    .addTypeConverter(CalendarTypeConverter(Gson()))
                    .build()
            }
            return instance
        }
    }
}