package com.dev.smartparking.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateBookingResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "bookingReference") val bookingReference: String,
)

@JsonClass(generateAdapter = true)
data class BookingResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "qrCode") val qrCode: String,
    @Json(name = "bookingReference") val bookingReference: String,
    @Json(name = "name") val name: String,
    @Json(name = "phone") val phoneNumber: String,
    @Json(name = "vehicle") val vehicle: String,
    @Json(name = "licencePlate") val licencePlate: String,
    @Json(name = "location") val location: String,
    @Json(name = "address") val address: String,
    @Json(name = "slotNumber") val slotNumber: String,
    @Json(name = "startTime") val startTime: String,
    @Json(name = "endTime") val endTime: String,
    @Json(name = "estimatedPrice") val estimatedPrice: Int,
    @Json(name = "adminFee") val adminFee: Int,
    @Json(name = "discount") val discount: Int,
    @Json(name = "payment") val payment: String,
    @Json(name = "status") val status: String
)