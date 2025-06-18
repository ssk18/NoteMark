package com.ssk.core.domain.notes

import com.ssk.core.domain.DataError
import com.ssk.core.domain.EmptyResult

interface NotesRepository {
    suspend fun createNote(note: Note): EmptyResult<DataError>
}