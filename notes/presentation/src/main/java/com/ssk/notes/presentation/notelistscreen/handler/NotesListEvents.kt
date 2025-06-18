package com.ssk.notes.presentation.notelistscreen.handler

import com.ssk.core.presentation.ui.UiText

sealed interface NotesListEvents {
    data class ShowError(val error: UiText): NotesListEvents
    data class NavigateToNoteDetail(val noteId: String): NotesListEvents
}