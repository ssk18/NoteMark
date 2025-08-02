package com.ssk.core.domain

import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.formatDate(): String {
    val instant = Instant.parse(this)
    val now = Instant.now()
    val duration = Duration.between(instant, now)
    
    return if (duration.toMinutes() < 5) {
        "Just now"
    } else {
        val formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm", Locale.ENGLISH)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        localDateTime.format(formatter)
    }
}