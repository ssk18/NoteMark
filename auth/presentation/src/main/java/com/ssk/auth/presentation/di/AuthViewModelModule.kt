package com.ssk.auth.presentation.di

import com.ssk.auth.presentation.registrationscreen.RegistrationViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegistrationViewModel)
}