package com.example.data.di

import android.content.Context
import com.example.data.db.room.dao.AlarmDao
import com.example.data.db.room.dao.StatsDao
import com.example.data.db.room.database.AlarmDatabase
import com.example.data.db.room.database.StatsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideGetAlarmDBInstance(@ApplicationContext context: Context): AlarmDao {
        return AlarmDatabase.getInstance(context)!!.alarmDao()
    }

    fun provideGetStatsDBInstance(@ApplicationContext context: Context): StatsDao {
        return StatsDatabase.getInstance(context)!!.statsDao()
    }
}