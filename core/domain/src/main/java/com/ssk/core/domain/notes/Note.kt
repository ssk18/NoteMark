package com.ssk.core.domain.notes

data class Note(
    val id: String, // UUID String
    val title: String,
    val content: String,
    val createdAt: String, // ISO 8601 String
    val lastEditedAt: String, // ISO 8601 String
)