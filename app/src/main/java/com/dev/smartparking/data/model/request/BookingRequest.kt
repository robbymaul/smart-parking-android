package com.dev.smartparking.data.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateBookingRequest(
    @Json(name = "vehicleId") val vehicleId: Int,
    @Json(name = "slotId") val slotId: Int,
    @Json(name = "placeId") val placeId: Int,
    @Json(name = "scheduledEntry") val scheduledEntry: String,
    @Json(name = "scheduledExit") val scheduledExit: String,
    @Json(name = "promoCodeId") val promoCodeId: String? = null,
)