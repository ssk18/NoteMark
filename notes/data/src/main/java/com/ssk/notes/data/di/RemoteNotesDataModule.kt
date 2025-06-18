package com.ssk.notes.data.di

import com.ssk.core.domain.notes.RemoteNotesDataSource
import com.ssk.notes.data.network.KtorRemoteNoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val remoteNotesDataModule = module {
    singleOf(::KtorRemoteNoteDataSource).bind<RemoteNotesDataSource>()
}