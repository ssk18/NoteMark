package com.ssk.core.data.notes

import com.ssk.core.domain.DataError
import com.ssk.core.domain.EmptyResult
import com.ssk.core.domain.Result
import com.ssk.core.domain.asEmptyDataResult
import com.ssk.core.domain.notes.LocalNotesDataSource
import com.ssk.core.domain.notes.Note
import com.ssk.core.domain.notes.NotesRepository
import com.ssk.core.domain.notes.RemoteNotesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

class NotesRepositoryImpl(
    private val localNotesDataSource: LocalNotesDataSource,
    private val remoteNotesDataSource: RemoteNotesDataSource,
    private val applicationScope: CoroutineScope
) : NotesRepository {
    override suspend fun createNote(note: Note): EmptyResult<DataError> {
        val localResult = localNotesDataSource.upsertNote(note)
        if (localResult !is Result.Success) {
            return localResult.asEmptyDataResult()
        }

        val noteWithId = note.copy(id = localResult.data)
        val remoteResult = remoteNotesDataSource.postNote(
            note = noteWithId
        )

        return when (remoteResult) {
            is Result.Error -> {
                Result.Success(Unit)
            }

            is Result.Success -> {
                applicationScope.async {
                    localNotesDataSource.upsertNote(remoteResult.data).asEmptyDataResult()
                }.await()
            }
        }
    }
}