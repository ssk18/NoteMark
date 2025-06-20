package com.ssk.notes.presentation.notesDetailscreen.handler

sealed interface NoteDetailsAction {
    data object OnNoteSaved : NoteDetailsAction
    data object OnCloseNoteClicked : NoteDetailsAction
    data class OnTitleChanged(val title: String) : NoteDetailsAction
    data class OnContentChanged(val content: String) : NoteDetailsAction

    data object OnDismissDialog : NoteDetailsAction
}