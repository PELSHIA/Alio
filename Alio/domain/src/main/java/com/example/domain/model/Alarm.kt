package com.example.domain.model

import java.util.*

data class Alarm(
    val id: String,
    val name: String,
    val time: Calendar,
    val category: String,
    val mission: String,
    val ringtone: RingTone
)
