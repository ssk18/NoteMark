package com.ssk.core.domain.notes

import com.ssk.core.domain.DataError
import com.ssk.core.domain.Result
import kotlinx.coroutines.flow.Flow

typealias NoteId = String

interface LocalNotesDataSource {
    suspend fun upsertNote(note: Note): Result<NoteId, DataError.Local>
    fun getNotes(): Flow<List<Note>>
}