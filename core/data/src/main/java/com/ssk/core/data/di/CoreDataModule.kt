package com.ssk.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.ssk.core.data.datastore.SessionStorageImpl
import com.ssk.core.data.network.HttpClientFactory
import com.ssk.core.data.notes.NotesRepositoryImpl
import com.ssk.core.database.NotesDatabase
import com.ssk.core.domain.SessionStorage
import com.ssk.core.domain.notes.NotesRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

private val Context.dataStore by preferencesDataStore(
    name = "tokens"
)

val coreDataModule = module {
    single<DataStore<Preferences>> {
        get<Context>().dataStore
    }
    
    single<SessionStorage> {
        SessionStorageImpl(get())
    }

    single {
        HttpClientFactory(get()).build()
    }

    singleOf(::NotesRepositoryImpl).bind<NotesRepository>()
}