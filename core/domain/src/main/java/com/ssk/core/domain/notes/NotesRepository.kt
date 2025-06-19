package com.ssk.core.domain.notes

import com.ssk.core.domain.DataError
import com.ssk.core.domain.EmptyResult
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun createNote(note: Note): EmptyResult<DataError>
    suspend fun fetchNotes(): EmptyResult<DataError>
    fun getNotes(): Flow<List<Note>>
}