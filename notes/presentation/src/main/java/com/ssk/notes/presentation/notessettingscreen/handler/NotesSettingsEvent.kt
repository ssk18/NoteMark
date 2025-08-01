package com.ssk.notes.presentation.notessettingscreen.handler

import com.ssk.core.presentation.ui.UiText

sealed interface NotesSettingsEvent {
    data object NavigateToNotesList : NotesSettingsEvent
    data object NavigateToLogin : NotesSettingsEvent
    data class ShowSnackBar(val message: UiText): NotesSettingsEvent
}