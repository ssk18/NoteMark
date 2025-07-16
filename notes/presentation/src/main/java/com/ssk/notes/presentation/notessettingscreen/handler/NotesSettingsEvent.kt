package com.ssk.notes.presentation.notessettingscreen.handler

sealed interface NotesSettingsEvent {
    data object NavigateToNotesList : NotesSettingsEvent
    data object NavigateToLogin : NotesSettingsEvent
}