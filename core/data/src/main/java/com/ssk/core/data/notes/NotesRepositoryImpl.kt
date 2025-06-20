package com.ssk.core.data.notes

import com.ssk.core.domain.DataError
import com.ssk.core.domain.EmptyResult
import com.ssk.core.domain.Result
import com.ssk.core.domain.asEmptyDataResult
import com.ssk.core.domain.notes.LocalNotesDataSource
import com.ssk.core.domain.notes.Note
import com.ssk.core.domain.notes.NoteId
import com.ssk.core.domain.notes.NotesRepository
import com.ssk.core.domain.notes.RemoteNotesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow

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

    override suspend fun fetchNotes(): EmptyResult<DataError> {
        return Result.Success(Unit)
//        val note = remoteNotesDataSource.getNotes().map { paginatedNotes ->
//            paginatedNotes.notes.map { it }
//        }.asEmptyDataResult()
//
//        return when (val result = remoteNotesDataSource.getNotes()) {
//            is Result.Error -> result.asEmptyDataResult()
//            is Result.Success -> {
//                applicationScope.async {
//                    localNotesDataSource.upsertNote(note).asEmptyDataResult()
//                }.await()
//            }
//        }
    }

    override fun getNotes(): Flow<List<Note>> {
        return localNotesDataSource.getNotes()
    }

    override fun getNoteById(id: NoteId): Flow<Note> {
        return localNotesDataSource.getNoteById(id)
    }

    override suspend fun updateNote(note: Note): EmptyResult<DataError> {
        return localNotesDataSource.upsertNote(note).asEmptyDataResult()
    }
}