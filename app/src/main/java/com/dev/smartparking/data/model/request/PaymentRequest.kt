package com.dev.smartparking.data.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaymentRequest(
    @Json(name = "paymentMethodId") val paymentMethodId: Int,
)