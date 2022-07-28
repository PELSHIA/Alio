package com.example.data.db.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "stats_db")
data class StatsEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val time: Calendar
)