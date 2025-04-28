package com.dev.smartparking.di

import com.dev.smartparking.data.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    // Repositories
    single { AuthRepository(get(), get()) }
}