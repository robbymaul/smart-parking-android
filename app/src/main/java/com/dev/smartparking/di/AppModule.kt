package com.dev.smartparking.di

import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.domain.usecase.AuthUseCase
import com.dev.smartparking.domain.usecase.UserUseCase
import com.dev.smartparking.viewmodel.ForgotPasswordViewModel
import com.dev.smartparking.viewmodel.IndexViewModel
import com.dev.smartparking.viewmodel.LoginViewModel
import com.dev.smartparking.viewmodel.OTPViewModel
import com.dev.smartparking.viewmodel.ProfileViewModel
import com.dev.smartparking.viewmodel.RegisterViewModel
import com.dev.smartparking.viewmodel.STNKActivationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // AuthPreferences
    single { AuthPreferences(androidContext()) }

    // UseCases
    factory {
        AuthUseCase(get())
    }
    factory {
        UserUseCase(get())
    }

    // ViewModels
    viewModel { RegisterViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { OTPViewModel(get())}
    viewModel { IndexViewModel(get(), get())}
    viewModel { ProfileViewModel(get(), get(), get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { STNKActivationViewModel(get()) }
}