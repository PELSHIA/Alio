package com.example.data.db.room.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.db.room.dao.AlarmDao

abstract class AlarmDatabase : RoomDatabase() {
    abstract fun AlarmDao(): AlarmDao

    companion object {
        private var instance: AlarmDatabase? = null

        fun getInstance(context: Context): AlarmDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlarmDatabase::class.java,
                    "bookmark_table"
                ).build()
            }
            return instance
        }
    }
}