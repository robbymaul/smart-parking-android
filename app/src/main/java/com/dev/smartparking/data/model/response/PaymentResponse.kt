package com.dev.smartparking.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaymentMethodResponse(
    @Json(name = "description") val description: String,
    @Json(name = "id") val id: Int,
    @Json(name = "methodName") val methodName: String,
    @Json(name = "methodType") val methodType: String,
    @Json(name = "provider") val provider: String,
)

@JsonClass(generateAdapter = true)
data class PaymentResponse(
    @Json(name = "orderId") val orderId: String,
    @Json(name = "redirectUrl") val redirectUrl: String,
    @Json(name = "token") val token: String,
    @Json(name = "bookingId") val bookingId: Int,
    @Json(name = "amount") val amount: Int,
)

@JsonClass(generateAdapter = true)
data class PaymentStatusResponse(
    @Json(name = "bookingId") val bookingId: Int,
    @Json(name = "bookingStatus") val bookingStatus: String,
    @Json(name = "paymentStatus") val paymentStatus: String,
    @Json(name = "amount") val amount: String,
    @Json(name = "paymentReference") val paymentReference: String,
)