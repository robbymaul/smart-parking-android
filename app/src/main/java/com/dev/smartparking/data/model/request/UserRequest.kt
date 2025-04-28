package com.dev.smartparking.data.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateVehiclesRequest(
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
)