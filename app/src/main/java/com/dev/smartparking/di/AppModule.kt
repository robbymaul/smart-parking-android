package com.dev.smartparking.di

import com.dev.smartparking.activity.PaymentActivity
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.domain.usecase.AuthUseCase
import com.dev.smartparking.domain.usecase.BookingUseCase
import com.dev.smartparking.domain.usecase.PlacesUseCase
import com.dev.smartparking.domain.usecase.UserUseCase
import com.dev.smartparking.viewmodel.DetailMallViewModel
import com.dev.smartparking.viewmodel.DetailTicketViewModel
import com.dev.smartparking.viewmodel.ForgotPasswordViewModel
import com.dev.smartparking.viewmodel.HomepageViewModel
import com.dev.smartparking.viewmodel.IndexViewModel
import com.dev.smartparking.viewmodel.LocationViewModel
import com.dev.smartparking.viewmodel.LoginViewModel
import com.dev.smartparking.viewmodel.OTPViewModel
import com.dev.smartparking.viewmodel.ParkingViewModel
import com.dev.smartparking.viewmodel.PaymentViewModel
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
    factory {
        PlacesUseCase(get())
    }
    factory { BookingUseCase(get()) }

    // ViewModels
    viewModel { RegisterViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { OTPViewModel(get())}
    viewModel { IndexViewModel(get(), get())}
    viewModel { ProfileViewModel(get(), get(), get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { STNKActivationViewModel(get()) }
    viewModel { HomepageViewModel(get())}
    viewModel { DetailMallViewModel(get(), get())}
    viewModel {LocationViewModel(get())}
    viewModel {ParkingViewModel(get(), get(), get())}
    viewModel { PaymentViewModel(get()) }
    viewModel { DetailTicketViewModel(get())}
}