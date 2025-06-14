package com.ssk.auth.data.di

import com.ssk.auth.data.repository.AuthRepositoryImpl
import com.ssk.auth.domain.repository.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}