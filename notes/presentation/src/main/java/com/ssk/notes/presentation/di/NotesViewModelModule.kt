package com.ssk.notes.presentation.di

import com.ssk.notes.presentation.notelistscreen.NotesListViewModel
import com.ssk.notes.presentation.notesDetailscreen.NoteDetailsViewModel
import com.ssk.notes.presentation.notessettingscreen.NotesSettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val notesViewModelModule = module {
    viewModelOf(::NotesListViewModel)
    viewModelOf(::NoteDetailsViewModel)
    viewModelOf(::NotesSettingsViewModel)
}