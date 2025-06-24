package com.ssk.notes.presentation.notelistscreen.handler

import com.ssk.core.domain.notes.Note

data class NotesListState(
    val userInitials: String = "",
    val notes: List<Note> = emptyList(),
    val showDeleteDialog: Boolean = false,
)
