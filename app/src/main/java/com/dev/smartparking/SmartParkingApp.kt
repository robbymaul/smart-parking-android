package com.dev.smartparking

import android.app.Application
import com.dev.smartparking.di.appModule
import com.dev.smartparking.di.networkModule
import com.dev.smartparking.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SmartParkingApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@SmartParkingApp)
            modules(listOf(appModule, networkModule, repositoryModule))
        }
    }
}