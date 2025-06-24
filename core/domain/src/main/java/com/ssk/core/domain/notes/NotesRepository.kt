package com.ssk.core.domain.notes

import com.ssk.core.domain.DataError
import com.ssk.core.domain.EmptyResult
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun createNote(note: Note): EmptyResult<DataError>
    suspend fun updateNote(note: Note): EmptyResult<DataError>
    suspend fun fetchNotes(): EmptyResult<DataError>
    fun getNotes(): Flow<List<Note>>

    fun getNoteById(id: NoteId): Flow<Note>

    suspend fun deleteNote(note: Note)
}