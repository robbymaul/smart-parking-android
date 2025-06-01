package com.dev.smartparking.di

import com.dev.smartparking.data.repository.AuthRepository
import com.dev.smartparking.data.repository.BookingRepository
import com.dev.smartparking.data.repository.PaymentRepository
import com.dev.smartparking.data.repository.PlacesRepository
import com.dev.smartparking.data.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    // Repositories
    single { AuthRepository(get(), get()) }
    single { UserRepository(get(), get()) }
    single { PlacesRepository(get(), get()) }
    single { BookingRepository(get(), get())}
    single { PaymentRepository(get(), get())}
}