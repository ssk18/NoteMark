package com.ssk.notemark

import android.app.Application
import com.ssk.auth.data.di.authDataModule
import com.ssk.auth.presentation.di.authViewModelModule
import com.ssk.core.data.di.coreDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class NoteMarkApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        
        startKoin {
            androidContext(this@NoteMarkApp)
            modules(
                authViewModelModule,
                coreDataModule,
                authDataModule
            )
        }
    }
}