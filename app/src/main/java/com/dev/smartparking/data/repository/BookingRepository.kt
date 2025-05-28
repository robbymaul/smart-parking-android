package com.dev.smartparking.data.repository

import android.util.Log
import com.dev.smartparking.data.api.ApiService
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.data.model.request.CreateBookingRequest
import com.dev.smartparking.data.model.request.LoginRequest
import com.dev.smartparking.data.model.response.BookingResponse
import com.dev.smartparking.data.model.response.CreateBookingResponse
import com.dev.smartparking.data.model.response.LoginResponse
import com.dev.smartparking.data.model.response.ResponseData
import com.dev.smartparking.data.utils.ErrorUtils
import com.dev.smartparking.data.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookingRepository(private val apiService: ApiService, authPreferences: AuthPreferences) {
    suspend fun createBooking(
        vehicleId: Int,
        slotId: Int,
        placeId: Int,
        scheduledEntry: String,
        scheduledExit: String,
        promoCodeId: String? = null
    ): Result<ResponseData<CreateBookingResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = CreateBookingRequest(
                    vehicleId = vehicleId,
                    slotId = slotId,
                    placeId = placeId,
                    scheduledEntry = scheduledEntry,
                    scheduledExit = scheduledExit,
                    promoCodeId = promoCodeId
                )

                Log.d("create booking request", "$request")

                val response = apiService.createBooking(request)

                if (response.isSuccessful) {
                    response.body()?.let { createBookingResponse ->
                        Result.Success(createBookingResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("create booking error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun getBooking(
        bookingId: Int,
    ): Result<ResponseData<BookingResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getBooking(bookingId = bookingId)

                if (response.isSuccessful) {
                    response.body()?.let { bookingResponse ->
                        Result.Success(bookingResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("get booking error", "$e")
                Result.Error(e)
            }
        }
    }
}