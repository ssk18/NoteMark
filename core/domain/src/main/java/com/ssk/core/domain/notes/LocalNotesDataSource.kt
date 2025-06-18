package com.ssk.core.domain.notes

import com.ssk.core.domain.DataError
import com.ssk.core.domain.Result

typealias NoteId = String

interface LocalNotesDataSource {
    suspend fun upsertNote(note: Note): Result<NoteId, DataError.Local>
}