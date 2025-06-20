package com.ssk.core.database

import android.database.sqlite.SQLiteFullException
import com.ssk.core.database.dao.NotesDao
import com.ssk.core.database.entity.toNote
import com.ssk.core.database.entity.toNoteEntity
import com.ssk.core.domain.DataError
import com.ssk.core.domain.Result
import com.ssk.core.domain.notes.LocalNotesDataSource
import com.ssk.core.domain.notes.Note
import com.ssk.core.domain.notes.NoteId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalNotesDataSource(
    private val notesDao: NotesDao
) : LocalNotesDataSource {
    override suspend fun upsertNote(note: Note): Result<NoteId, DataError.Local> {
        return try {
            val noteEntity = note.toNoteEntity()
            notesDao.createNote(noteEntity)
            Result.Success(note.id)
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override fun getNotes(): Flow<List<Note>> {
        return notesDao.getNotes()
            .map { notes ->
                notes.map { it.toNote() }
            }
    }

    override fun getNoteById(id: NoteId): Flow<Note> {
        return notesDao.getNoteById(id).map { it.toNote() }
    }
}