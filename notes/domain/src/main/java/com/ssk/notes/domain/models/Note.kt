package com.ssk.notes.domain.models

data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
)
