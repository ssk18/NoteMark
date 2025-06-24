package com.ssk.notes.presentation.notelistscreen.handler

import com.ssk.core.domain.notes.Note

sealed interface NotesListAction {
    data class OnNoteClicked(val note: Note) : NotesListAction
    data object OnAddNoteClicked : NotesListAction
    data object OnDeleteNoteConfirmed: NotesListAction
    data class OnLongPressNote(val noteId: String) : NotesListAction

    data object OnDialogDismiss : NotesListAction
}