package com.ssk.notes.presentation.notessettingscreen.handler

sealed interface NotesSettingAction {
    data object OnBackClick : NotesSettingAction
    data object OnLogOutClick : NotesSettingAction
}