package com.example.data.db.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.RingTone
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "alarm_db")
data class AlarmEntity(
    @PrimaryKey val id: String,
    val name: String,
    val time: Calendar,
    val dayOfWeek: List<Boolean>,
    val category: String,
    val mission: String,
    val ringtone: RingTone
)
