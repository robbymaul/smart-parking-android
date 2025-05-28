package com.dev.smartparking.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dev.smartparking.domain.model.Booking
import com.dev.smartparking.domain.usecase.BookingUseCase
import com.dev.smartparking.route.Screen
import kotlinx.coroutines.launch

class PaymentViewModel(private val bookingUseCase: BookingUseCase) : ViewModel() {
    // State
    var bookingModel by mutableStateOf<Booking?>(null)
        private set

    var errorMessage by mutableStateOf("")
        private set

    // UI State
    var isLoading by mutableStateOf(false)
        private set

    var isGetBookingSuccessful by mutableStateOf(false)
        private set

    var isGetBookingFailed by mutableStateOf(false)
        private set

    // handler
    fun onIsGetBookingSuccessfulChange(value: Boolean) {
        isGetBookingSuccessful = value
    }

    fun onIsGetBookingFailedChange(value: Boolean) {
        isGetBookingFailed = value
    }


    fun handleClickNavigationIcon(navController: NavHostController?) {
        navController?.popBackStack()
    }

    fun handleClickPayment(navController: NavHostController?) {
        navController?.navigate(Screen.DetailTicket.route) {
            popUpTo(Screen.Payment.route) {
                inclusive = true
            }
        }
    }

    // Fetch
    fun getBooking(bookingId: Int, onFailed: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true

                val result = bookingUseCase.getBooking(bookingId)

                isLoading = result.isLoading()

                result.getOrNull()?.let { booking ->
                    Log.d("booking data", "$booking")
                    bookingModel = booking
                } ?: run {
                    errorMessage =
                        result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                    isGetBookingFailed = true
                }

                Log.d("booking", "$bookingModel")

                if (isGetBookingFailed) {
                    onFailed()
                }
            } catch (e: Exception) {
                isGetBookingFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
                if (isGetBookingFailed) {
                    onFailed()
                }
            } finally {
                isLoading = false
            }
        }
    }

}