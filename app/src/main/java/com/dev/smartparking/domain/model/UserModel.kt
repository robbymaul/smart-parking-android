package com.dev.smartparking.domain.model

data class UserModel (
    val username: String,
    val email: String,
    val phoneNumber: String,
    val accountType: String,
    val emailVerified: Boolean,
    val phoneVerified: Boolean,
    val accountStatus: String,
    val isVehicleActivated: Boolean
)

data class VehicleModel (
    val id: Int,
    val licensePlate: String,
    val vehicleType: String,
    val brand: String,
    val model: String,
    val color: String,
    val rfidTag: String,
    val length: Double,
    val width: Double,
    val height: Double,
    val isActive: Boolean,
    val createdAt: String,
    val updatedAt: String,
)