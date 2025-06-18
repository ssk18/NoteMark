package com.ssk.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ssk.core.database.dao.NotesDao
import com.ssk.core.database.entity.NoteEntity

@Database(
    entities = [
        NoteEntity::class
    ],
    version = 1,
    exportSchema = true,
    views = [
    ]
)
abstract class NotesDatabase: RoomDatabase() {
    abstract val notesDao: NotesDao
}