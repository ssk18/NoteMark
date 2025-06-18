package com.ssk.core.domain.notes

import com.ssk.core.domain.DataError
import com.ssk.core.domain.Result

data class PaginatedNotes(
    val notes: List<Note>,
    val total: Int
)

interface RemoteNotesDataSource {
    suspend fun postNote(note: Note): Result<Note, DataError.Network>
    suspend fun getNotes(page: Int = -1, size: Int = 10): Result<PaginatedNotes, DataError.Network>
}