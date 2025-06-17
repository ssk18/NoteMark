package com.ssk.notes.presentation.notelistscreen.handler

import com.ssk.notes.domain.models.Note

data class NotesListState(
    val userInitials: String = "",
    val note: Note? = null
)
