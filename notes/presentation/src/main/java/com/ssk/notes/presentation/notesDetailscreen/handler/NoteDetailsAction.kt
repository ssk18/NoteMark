package com.ssk.notes.presentation.notesDetailscreen.handler

sealed interface NoteDetailsAction {
    data object OnNoteSaved : NoteDetailsAction
    data object OnCloseNoteClicked : NoteDetailsAction
}