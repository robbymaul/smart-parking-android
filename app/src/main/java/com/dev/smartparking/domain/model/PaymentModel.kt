package com.dev.smartparking.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class PaymentMethod(
    val description: String,
    val id: Int,
    val methodName: String,
    val methodType: String,
    val provider: String,
)

data class Payment(
    val orderId: String,
    val redirectUrl: String,
    val token: String,
    val bookingId: Int,
    val amount: Int,
)


data class PaymentStatus(
    val bookingId: Int,
    val bookingStatus: String,
    val paymentStatus: String,
    val amount: String,
    val paymentReference: String,
)