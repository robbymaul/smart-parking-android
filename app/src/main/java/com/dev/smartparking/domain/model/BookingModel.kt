package com.dev.smartparking.domain.model

data class CreateBooking(
    val id: Int,
    val bookingReference: String
)

data class Booking(
    val id: Int,
    val qrCode: String,
    val bookingReference: String,
    val name: String,
    val phoneNumber: String,
    val vehicle: String,
    val licencePlate: String,
    val location: String,
    val address: String,
    val slotNumber: String,
    val startTime: String,
    val endTime: String,
    val estimatedPrice: Int,
    val adminFee: Int,
    val discount: Int,
    val payment: String,
    val status: String
)
