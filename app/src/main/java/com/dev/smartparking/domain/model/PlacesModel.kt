package com.dev.smartparking.domain.model

import com.dev.smartparking.data.model.response.UserProfileResponse
import com.squareup.moshi.Json

data class OperatingHourModel(
    val id: Int,
    val placeId: Int,
    val dayOfWeek: String,
    val openingTime: String,
    val closingTime: String,
    val is24Hours: Boolean,
    val isClosed: Boolean
)

data class TariffRateModel(
    val id: Int,
    val planId: Int,
    val vehicleType: String,
    val slotType: String,
    val startTime: String,
    val endTime: String,
    val dayCategory: String,
    val basePrice: String,
    val hourlyRate: String,
    val dayRate: String,
    val minimumCharge: String,
    val gracePeriodMinutes: Int
)

data class TariffPlanModel(
    val id: Int,
    val placeId: Int,
    val planName: String,
    val description: String,
    val effectiveFrom: String,
    val effectiveUntil: String,
    val isActive: Boolean,
    val tariffRates: List<TariffRateModel>? = null
)

data class PlacesModel(
    val id: Int,
    val name: String,
    val image: String,
    val placeType: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val contactNumber: String,
    val email: String,
    val description: String,
    val totalCapacity: Int,
    val isActive: Boolean,
    val createdAt: String,
    val updatedAt: String?,
    val operatingHours: List<OperatingHourModel>? = null , // List of operating hours
    val tariffPlans: List<TariffPlanModel>? = null       // List of tariff plans
)

data class PlacesRatingModel(
    val id: Int,
    val user: UserProfileModel?,
    val ratingScore: Int,
    val reviewComment: String
)

data class ParkingZoneModel(
    val id: Int,
    val zoneName: String,
    val floorLevel: String,
    val zoneType: String,
    val totalSlots: Int,
    val isActive: Boolean
)

data class ParkingSlotModel(
    val id: Int,
    val zoneId: Int,
    val slotNumber: String,
    val slotType: String,
    val isReserved: Boolean,
    val isOccupied: Boolean,
    val isDisabledFriendly: Boolean,
    val hasEvCharger: Boolean,
    val isActive: Boolean,
//    val slotAvailability: List<SlotAvailabilityModel>
)

data class SlotAvailabilityModel(
    val id: Int,
    val slotId: Int,
    val availableFrom: String,
    val availableUntil: String,
    val isBookable: Boolean,
    val statusReason: String,
)
