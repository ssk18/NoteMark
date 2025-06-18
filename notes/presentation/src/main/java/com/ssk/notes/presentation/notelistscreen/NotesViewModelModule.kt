package com.ssk.notes.presentation.notelistscreen

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val notesViewModelModule = module {
    viewModelOf(::NotesListViewModel)
}