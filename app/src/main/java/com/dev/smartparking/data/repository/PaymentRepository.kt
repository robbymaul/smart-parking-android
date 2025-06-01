package com.dev.smartparking.data.repository

import android.util.Log
import com.dev.smartparking.data.api.ApiService
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.data.model.request.CreateBookingRequest
import com.dev.smartparking.data.model.request.PaymentRequest
import com.dev.smartparking.data.model.response.BookingResponse
import com.dev.smartparking.data.model.response.CreateBookingResponse
import com.dev.smartparking.data.model.response.PaymentMethodResponse
import com.dev.smartparking.data.model.response.PaymentResponse
import com.dev.smartparking.data.model.response.PaymentStatusResponse
import com.dev.smartparking.data.model.response.ResponseData
import com.dev.smartparking.data.model.response.TicketBookingResponse
import com.dev.smartparking.data.utils.ErrorUtils
import com.dev.smartparking.data.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PaymentRepository(private val apiService: ApiService, authPreferences: AuthPreferences) {
    suspend fun payment(
        bookingId: Int,
        paymentMethodId: Int
    ): Result<ResponseData<PaymentResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = PaymentRequest(
                    paymentMethodId = paymentMethodId
                )

                Log.d("payment request", "$request")

                val response = apiService.payment(bookingId = bookingId, paymentRequest = request)

                if (response.isSuccessful) {
                    response.body()?.let { paymentResponse ->
                        Result.Success(paymentResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("payment error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun getPaymentMethod(): Result<ResponseData<List<PaymentMethodResponse>>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.paymentMethod()

                if (response.isSuccessful) {
                    response.body()?.let { paymentMethodResponse ->
                        Result.Success(paymentMethodResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("get payment method error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun checkStatusPayment(bookingId: Int): Result<ResponseData<PaymentStatusResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.checkPayment(bookingId = bookingId )

                if (response.isSuccessful) {
                    response.body()?.let { checkStatusPaymentResponse ->
                        Result.Success(checkStatusPaymentResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("check status payment error", "$e")
                Result.Error(e)
            }
        }
    }
}