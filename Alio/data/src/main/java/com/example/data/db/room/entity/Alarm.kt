package com.example.data.db.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.entity.RingTone
import java.util.*

@Entity(tableName = "alarm_db")
data class Alarm(
    @PrimaryKey val id: String,
    val name: String,
    val time: Calendar,
    val category: String,
    val mission: String,
    val ringtone: RingTone
)
