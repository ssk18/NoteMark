package com.ssk.notemark.di

import com.ssk.notemark.MainViewModel
import com.ssk.notemark.NoteMarkApp
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<CoroutineScope> {
        (androidApplication() as NoteMarkApp).applicationScope
    }
    
    viewModelOf(::MainViewModel)
}