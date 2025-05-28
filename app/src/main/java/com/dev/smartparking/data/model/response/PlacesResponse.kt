package com.dev.smartparking.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlacesResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "image") val image: String,
    @Json(name = "placeType") val placeType: String,
    @Json(name = "address") val address: String,
    @Json(name = "latitude") val latitude: Double,
    @Json(name = "longitude") val longitude: Double,
    @Json(name = "contactNumber") val contactNumber: String,
    @Json(name = "email") val email: String,
    @Json(name = "description") val description: String,
    @Json(name = "totalCapacity") val totalCapacity: Int,
    @Json(name = "isActive") val isActive: Boolean,
    @Json(name = "createdAt") val createdAt: String,
    @Json(name = "updatedAt") val updatedAt: String,
    @Json(name = "operatingHour") val operatingHour: List<OperatingHour>? = null,
    @Json(name = "tariffPlan") val tariffPlan: List<TariffPlan>? = null
)

@JsonClass(generateAdapter = true)
data class OperatingHour(
    @Json(name = "id") val id: Int,
    @Json(name = "placeId") val placeId: Int,
    @Json(name = "dayOfWeek") val dayOfWeek: String,
    @Json(name = "openingTime") val openingTime: String,
    @Json(name = "closingTime") val closingTime: String,
    @Json(name = "is24Hours") val is24Hours: Boolean,
    @Json(name = "isClosed") val isClosed: Boolean
)

@JsonClass(generateAdapter = true)
data class TariffPlan(
    @Json(name = "id") val id: Int,
    @Json(name = "placeId") val placeId: Int,
    @Json(name = "planName") val planName: String,
    @Json(name = "description") val description: String,
    @Json(name = "effectiveFrom") val effectiveFrom: String,
    @Json(name = "effectiveUntil") val effectiveUntil: String,
    @Json(name = "isActive") val isActive: Boolean,
    @Json(name = "tariffRate") val tariffRate: List<TariffRate>? = null
)

@JsonClass(generateAdapter = true)
data class TariffRate(
    @Json(name = "id") val id: Int,
    @Json(name = "planId") val planId: Int,
    @Json(name = "vehicleType") val vehicleType: String,
    @Json(name = "slotType") val slotType: String,
    @Json(name = "startTime") val startTime: String,
    @Json(name = "endTime") val endTime: String,
    @Json(name = "dayCategory") val dayCategory: String,
    @Json(name = "basePrice") val basePrice: String,
    @Json(name = "hourlyRate") val hourlyRate: String,
    @Json(name = "dayRate") val dayRate: String,
    @Json(name = "minimumCharge") val minimumCharge: String,
    @Json(name = "gracePeriodMinutes") val gracePeriodMinutes: Int
)


@JsonClass(generateAdapter = true)
data class PlacesRatingResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "user") val user: UserProfileResponse?,
    @Json(name = "ratingScore") val ratingScore: Int,
    @Json(name = "reviewComment") val reviewComment: String
)

@JsonClass(generateAdapter = true)
data class ParkingZoneResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "placeId") val placeId: Int,
    @Json(name = "zoneName") val zoneName: String,
    @Json(name = "floorLevel") val floorLevel: String,
    @Json(name = "zoneType") val zoneType: String,
    @Json(name = "totalSlots") val totalSlots: Int,
    @Json(name = "isActive") val isActive: Boolean
)

@JsonClass(generateAdapter = true)
data class ParkingSlotResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "zoneId") val zoneId: Int,
    @Json(name = "slotNumber") val slotNumber: String,
    @Json(name = "slotType") val slotType: String,
    @Json(name = "isReserved") val isReserved: Boolean,
    @Json(name = "isOccupied") val isOccupied: Boolean,
    @Json(name = "isDisabledFriendly") val isDisabledFriendly: Boolean,
    @Json(name = "hasEvCharger") val hasEvCharger: Boolean,
    @Json(name = "isActive") val isActive: Boolean,
//    @Json(name = "SlotAvailability") val slotAvailability: List<SlotAvailabilityResponse>
)

@JsonClass(generateAdapter = true)
data class SlotAvailabilityResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "slotId") val slotId: Int,
    @Json(name = "availableFrom") val availableFrom: String,
    @Json(name = "availableUntil") val availableUntil: String,
    @Json(name = "isBookable") val isBookable: Boolean,
    @Json(name = "statusReason") val statusReason: String,
)