package com.dev.smartparking.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse (
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "phoneNumber") val phoneNumber: String,
    @Json(name = "accountType") val accountType: String,
    @Json(name = "emailVerified") val emailVerified: Boolean,
    @Json(name = "phoneVerified") val phoneVerified: Boolean,
    @Json(name = "accountStatus") val accountStatus: String,
    @Json(name = "isVehicleActivated") val isVehicleActivated: Boolean
)

@JsonClass(generateAdapter =  true)
data class UserProfileResponse(
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
    @Json(name = "profilePhoto") val profilePhoto: String?,
    @Json(name = "gender") val gender: String?,
    @Json(name = "dateOfBirth") val dateOfBirth: String?,
    @Json(name = "address") val address: String?,
    @Json(name = "city") val city: String?,
    @Json(name = "state") val state: String?,
    @Json(name = "postalCode") val postalCode: String?,
    @Json(name = "country") val country: String?
)

@JsonClass(generateAdapter = true)
data class CreateVehiclesResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "licensePlate") val licensePlate: String,
    @Json(name = "vehicleType") val vehicleType: String,
    @Json(name = "brand") val brand: String,
    @Json(name = "model") val model: String,
    @Json(name = "color") val color: String,
    @Json(name = "rfidTag") val rfidTag: String,
    @Json(name = "length") val length: Double,
    @Json(name = "width") val width: Double,
    @Json(name = "height") val height: Double,
    @Json(name = "isActive") val isActive: Boolean,
    @Json(name = "createdAt") val createdAt: String,
    @Json(name = "updatedAt") val updatedAt: String,
)

@JsonClass(generateAdapter = true)
data class GetVehiclesResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "licensePlate") val licensePlate: String,
    @Json(name = "vehicleType") val vehicleType: String,
    @Json(name = "brand") val brand: String,
    @Json(name = "model") val model: String,
    @Json(name = "color") val color: String,
    @Json(name = "rfidTag") val rfidTag: String,
    @Json(name = "length") val length: Double,
    @Json(name = "width") val width: Double,
    @Json(name = "height") val height: Double,
    @Json(name = "isActive") val isActive: Boolean,
    @Json(name = "createdAt") val createdAt: String = "",
    @Json(name = "updatedAt") val updatedAt: String = "",
)

@JsonClass(generateAdapter = true)
data class UpdatePasswordResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "message") val message: String,
)