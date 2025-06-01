package com.dev.smartparking.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dev.smartparking.domain.model.Booking
import com.dev.smartparking.domain.model.Payment
import com.dev.smartparking.domain.model.PaymentMethod
import com.dev.smartparking.domain.model.PaymentStatus
import com.dev.smartparking.domain.usecase.BookingUseCase
import com.dev.smartparking.domain.usecase.PaymentUseCase
import com.dev.smartparking.route.Screen
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val bookingUseCase: BookingUseCase,
    private val paymentUseCase: PaymentUseCase
) : ViewModel() {
    // State
    var bookingModel by mutableStateOf<Booking?>(null)
        private set

    var paymentMethodModel by mutableStateOf<List<PaymentMethod>>(listOf())
        private set

    var paymentStatusModel by mutableStateOf<PaymentStatus?>(null)
        private set

    var paymentModel by mutableStateOf<Payment?>(null)
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

    var isGetListPaymentMethodFailed by mutableStateOf(false)
        private set

    var isPaymentSuccessful by mutableStateOf(false)
        private set

    var isPaymentFailed by mutableStateOf(false)
        private set

    var isCheckPaymentSuccessful by mutableStateOf(false)
        private set

    var isCheckPaymentFailed by mutableStateOf(false)
        private set

    // handler
    fun onIsGetBookingSuccessfulChange(value: Boolean) {
        isGetBookingSuccessful = value
    }

    fun onIsGetBookingFailedChange(value: Boolean) {
        isGetBookingFailed = value
    }

    fun onIsGetListPaymentMethodFailedChange(value: Boolean) {
        isGetBookingFailed = value
    }

    fun onIsPaymentSuccessfulChange(value: Boolean) {
        isPaymentSuccessful = value
    }

    fun onIsPaymentFailedChange(value: Boolean) {
        isPaymentFailed = value
    }

    fun onIsCheckPaymentSuccessfulChange(value: Boolean) {
        isCheckPaymentSuccessful = value
    }

    fun onIsCheckPaymentFailed(value: Boolean) {
        isCheckPaymentFailed = value
    }



    fun handleClickNavigationIcon(navController: NavHostController) {
        navController.popBackStack()
    }

    fun handleClickPayment(navController: NavHostController) {
        navController.navigate(Screen.DetailTicket.route) {
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

    fun getListPaymentMethod(onFailed: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true

                val result = paymentUseCase.getListPaymentMethod()

                isLoading = result.isLoading()

                result.getOrNull()?.let { paymentMethod ->
                    Log.d("payment method data", "$paymentMethodModel")
                    paymentMethodModel = paymentMethod
                } ?: run {
                    errorMessage =
                        result.exceptionOrNull()?.message
                            ?: "Terjadi Kesalahan saat get list payment method"
                    isGetListPaymentMethodFailed = true
                }

                if (isGetBookingFailed) {
                    onFailed()
                }
            } catch (e: Exception) {
                isGetListPaymentMethodFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
                if (isGetBookingFailed) {
                    onFailed()
                }
            } finally {
                isLoading = false
            }
        }
    }

    fun checkStatusPayment(bookingId: Int, onSuccess: () -> Unit, onFailed: () -> Unit) {
        viewModelScope.launch {
            try {
                Log.d("trigger check status payment", "$bookingId")
                isLoading = true

                val result = paymentUseCase.checkStatusPayment(bookingId)

                isLoading = result.isLoading()

                result.getOrNull()?.let { paymentStatus ->
                    Log.d("payment status data", "$paymentStatus")
                    paymentStatusModel = paymentStatus
                    isCheckPaymentSuccessful = true
                } ?: run {
                    errorMessage =
                        result.exceptionOrNull()?.message
                            ?: "Terjadi Kesalahan saat get list payment method"
                    isCheckPaymentFailed = true
                }

                if (isCheckPaymentSuccessful) {
                    onSuccess()
                }

                if (isCheckPaymentFailed) {
                    onFailed()
                }
            } catch (e: Exception) {
                isCheckPaymentFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
                if (isCheckPaymentFailed) {
                    onFailed()
                }
            } finally {
                isLoading = false
            }
        }
    }

    fun payment(bookingId: Int, paymentMethodId: Int, onSuccess: () -> Unit, onFailed: () -> Unit) {
        Log.d("booking", "$bookingId")
        Log.d("paymentMethodId", "$paymentMethodId")
        return
        viewModelScope.launch {
            try {
                isLoading = true

                val result = paymentUseCase.payment(
                    bookingId, paymentMethodId
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let {
                    paymentModel = it
                    isPaymentSuccessful = true
                } ?: run {
                    isPaymentFailed = true
                    errorMessage =
                        result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                }

                if (isPaymentSuccessful) {
                    onSuccess()
                }

                if (isPaymentFailed) {
                    onFailed()
                }
            } catch (e: Exception) {
                isPaymentFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"

                if (isPaymentFailed) {
                    onFailed()
                }
            } finally {
                isLoading = false
            }
        }
    }
}