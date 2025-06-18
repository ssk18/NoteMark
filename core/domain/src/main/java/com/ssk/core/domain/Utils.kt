package com.ssk.core.domain

import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatDate(isoString: String): String {
    val instant = Instant.parse(isoString)
    val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH)
    return instant.atZone(java.time.ZoneId.systemDefault()).format(formatter)
}