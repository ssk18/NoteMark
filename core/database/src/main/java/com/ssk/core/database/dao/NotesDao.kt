package com.ssk.core.database.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.ssk.core.database.entity.NoteEntity

@Dao
interface NotesDao {

    @Upsert
    suspend fun createNote(note: NoteEntity)

}