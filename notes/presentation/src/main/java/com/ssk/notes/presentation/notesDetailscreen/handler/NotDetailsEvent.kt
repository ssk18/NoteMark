package com.ssk.notes.presentation.notesDetailscreen.handler

import com.ssk.core.presentation.ui.UiText

sealed interface NoteDetailsEvent {
    data class ShowError(val error: UiText): NoteDetailsEvent
    data object NavigateToNotesList: NoteDetailsEvent
}