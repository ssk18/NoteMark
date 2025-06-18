package com.ssk.notemark

import android.app.Application
import com.ssk.auth.data.di.authDataModule
import com.ssk.auth.presentation.di.authViewModelModule
import com.ssk.core.data.di.coreDataModule
import com.ssk.core.database.di.coreDatabaseModule
import com.ssk.notemark.di.appModule
import com.ssk.notes.data.di.remoteNotesDataModule
import com.ssk.notes.presentation.notelistscreen.notesViewModelModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class NoteMarkApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        
        startKoin {
            androidContext(this@NoteMarkApp)
            modules(
                appModule,
                authViewModelModule,
                coreDataModule,
                authDataModule,
                notesViewModelModule,
                coreDatabaseModule,
                remoteNotesDataModule,
            )
        }
    }
}