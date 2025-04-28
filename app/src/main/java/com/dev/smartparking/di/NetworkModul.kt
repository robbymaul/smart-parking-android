package com.dev.smartparking.di

import com.dev.smartparking.data.api.ApiService
import com.dev.smartparking.data.api.NetworkModule
import org.koin.dsl.module

val networkModule = module {
    // Moshi
    single { NetworkModule.moshi }

    // OkHttpClient
    single { NetworkModule.okHttpClient }

    // Retrofit
    single { NetworkModule.retrofit }

    // ApiService
    single<ApiService> { NetworkModule.apiService }
}