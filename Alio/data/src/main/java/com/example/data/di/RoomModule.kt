package com.example.data.di

import android.content.Context
import com.example.data.db.room.dao.AlarmDao
import com.example.data.db.room.database.AlarmDatabase
import com.google.gson.Gson
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
    fun provideGetRoomInstance(@ApplicationContext context: Context): AlarmDao {
        return AlarmDatabase.getInstance(context)!!.alarmDao()
    }
}