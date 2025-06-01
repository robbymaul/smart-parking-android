package com.dev.smartparking.domain.usecase

import com.dev.smartparking.data.repository.BookingRepository
import com.dev.smartparking.data.repository.PaymentRepository
import com.dev.smartparking.data.utils.Result
import com.dev.smartparking.domain.model.Booking
import com.dev.smartparking.domain.model.CreateBooking
import com.dev.smartparking.domain.model.Payment
import com.dev.smartparking.domain.model.PaymentMethod
import com.dev.smartparking.domain.model.PaymentStatus
import com.dev.smartparking.domain.model.TicketBooking


class PaymentUseCase(private val paymentRepository: PaymentRepository) {
    suspend fun payment(
        bookingId: Int,
        paymentMethodId: Int
    ): Result<Payment> {
        val result = paymentRepository.payment(
            bookingId = bookingId,
            paymentMethodId = paymentMethodId
        )

        return when (result) {
            is Result.Success -> {
                val paymentData = result.data.data
                Result.Success(
                    Payment(
                        orderId = paymentData.orderId,
                        redirectUrl = paymentData.redirectUrl,
                        token = paymentData.token,
                        bookingId = paymentData.bookingId,
                        amount = paymentData.amount
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun getListPaymentMethod(): Result<List<PaymentMethod>> {
        return when (val result = paymentRepository.getPaymentMethod()) {
            is Result.Success -> {
                val paymentMethodData = result.data.data
                val paymentMethods = paymentMethodData.map {
                    PaymentMethod(
                        description = it.description,
                        id = it.id,
                        methodName = it.methodName,
                        methodType = it.methodType,
                        provider = it.provider
                    )
                }
                Result.Success(
                    paymentMethods
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun checkStatusPayment(bookingId: Int): Result<PaymentStatus> {
        return when (val result = paymentRepository.checkStatusPayment(bookingId)) {
            is Result.Success -> {
                val paymentStatus = result.data.data
                Result.Success(
                    PaymentStatus(
                        bookingId = paymentStatus.bookingId,
                        bookingStatus = paymentStatus.bookingStatus,
                        paymentStatus = paymentStatus.paymentStatus,
                        amount = paymentStatus.amount,
                        paymentReference = paymentStatus.paymentReference
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }
}