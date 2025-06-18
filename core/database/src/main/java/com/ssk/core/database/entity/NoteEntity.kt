package com.ssk.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ssk.core.domain.notes.Note

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val id: String, // UUID String
    val title: String,
    val content: String,
    val createdAt: String, // ISO 8601 String
    val lastEditedAt: String, // ISO 8601 String
)


fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        lastEditedAt = lastEditedAt
    )
}

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        lastEditedAt = lastEditedAt
    )
}