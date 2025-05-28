package com.dev.smartparking.domain.model

import com.squareup.moshi.Json

data class UserModel(
    val username: String,
    val email: String,
    val phoneNumber: String,
    val accountType: String,
    val emailVerified: Boolean,
    val phoneVerified: Boolean,
    val accountStatus: String,
    val isVehicleActivated: Boolean
)

data class UserProfileModel(
    val firstName: String,
    val lastName: String,
    val profilePhoto: String?,
    val gender: String?,
    val dateOfBirth: String?,
    val address: String?,
    val city: String?,
    val state: String?,
    val postalCode: String?,
    val country: String?
)

data class VehicleModel(
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
    val createdAt: String = "",
    val updatedAt: String = "",
)

data class UpdatePasswordModel(
    val success: Boolean,
    val message: String
)