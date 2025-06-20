package com.ssk.notes.presentation.notesDetailscreen.handler

data class NoteDetailState(
    val title: String = "",
    val content: String = "",
    val createdAt: String? = null,
    val lastEditedAt: String? = null
)
