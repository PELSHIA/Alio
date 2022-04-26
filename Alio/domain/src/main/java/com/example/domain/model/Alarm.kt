package com.example.domain.model

import java.util.*
import kotlin.collections.ArrayList

data class Alarm(
    val id: String,
    val name: String,
    val time: Calendar,
    val dayOfWeek: List<Boolean>,
    val category: String,
    val mission: String,
    val ringtone: RingTone
)
