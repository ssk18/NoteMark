package com.ssk.core.database.di

import androidx.room.Room
import com.ssk.core.database.NotesDatabase
import com.ssk.core.database.RoomLocalNotesDataSource
import com.ssk.core.domain.notes.LocalNotesDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            NotesDatabase::class.java,
            "notes.db"
        ).build()
    }

    single {
        get<NotesDatabase>().notesDao
    }

    singleOf(::RoomLocalNotesDataSource).bind<LocalNotesDataSource>()
}