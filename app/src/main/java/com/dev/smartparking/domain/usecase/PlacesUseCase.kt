package com.dev.smartparking.domain.usecase

import android.util.Log
import com.dev.smartparking.data.repository.PlacesRepository
import com.dev.smartparking.data.utils.Result
import com.dev.smartparking.domain.model.OperatingHourModel
import com.dev.smartparking.domain.model.ParkingSlotModel
import com.dev.smartparking.domain.model.ParkingZoneModel
import com.dev.smartparking.domain.model.PlacesModel
import com.dev.smartparking.domain.model.PlacesRatingModel
import com.dev.smartparking.domain.model.TariffPlanModel
import com.dev.smartparking.domain.model.TariffRateModel
import com.dev.smartparking.domain.model.UserProfileModel

class PlacesUseCase(private val placesRepository: PlacesRepository) {
    suspend fun getPlaces(
        page: Int,
        limit: Int,
        search: String = "",
        city: String = "",
        area: String = ""
    ): Result<List<PlacesModel>> {
        return when (val result = placesRepository.getPlaces(
            page = page,
            limit = limit,
            search = search,
            city = city,
            area = area
        )) {
            is Result.Success -> {
                val placesData = result.data.data
                val places = placesData.map {
                    Log.d("place data map", "trigger")
                    PlacesModel(
                        id = it.id,
                        placeType = it.placeType,
                        email = it.email,
                        name = it.name,
                        isActive = it.isActive,
                        description = it.description,
                        latitude = it.latitude,
                        createdAt = it.createdAt,
                        longitude = it.longitude,
                        updatedAt = it.updatedAt,
                        contactNumber = it.contactNumber,
                        totalCapacity = it.totalCapacity,
                        image = it.image,
                        address = it.address
                    )
                }

                Result.Success(places)
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun getDetailPlaces(mallId: Int): Result<PlacesModel> {
        return when (val result = placesRepository.getDetailPlaces(mallId = mallId)) {
            is Result.Success -> {
                val placesData = result.data.data
                val operatingHours = placesData.operatingHour?.map {
                    OperatingHourModel(
                        id = it.id,
                        placeId = it.placeId,
                        dayOfWeek = it.dayOfWeek,
                        openingTime = it.openingTime,
                        closingTime = it.closingTime,
                        is24Hours = it.is24Hours,
                        isClosed = it.isClosed
                    )
                }

                val tariffPlans = placesData.tariffPlan?.map { plan ->
                    TariffPlanModel(
                        id = plan.id,
                        placeId = plan.placeId,
                        planName = plan.planName,
                        description = plan.description,
                        effectiveFrom = plan.effectiveFrom,
                        effectiveUntil = plan.effectiveUntil,
                        isActive = plan.isActive,
                        tariffRates = plan.tariffRate?.map { rate ->
                            TariffRateModel(
                                id = rate.id,
                                planId = rate.planId,
                                vehicleType = rate.vehicleType,
                                slotType = rate.slotType,
                                startTime = rate.startTime,
                                endTime = rate.endTime,
                                dayCategory = rate.dayCategory,
                                basePrice = rate.basePrice,
                                hourlyRate = rate.hourlyRate,
                                dayRate = rate.dayRate,
                                minimumCharge = rate.minimumCharge,
                                gracePeriodMinutes = rate.gracePeriodMinutes
                            )
                        }
                    )
                }
                Result.Success(
                    PlacesModel(
                        id = placesData.id,
                        placeType = placesData.placeType,
                        email = placesData.email,
                        name = placesData.name,
                        isActive = placesData.isActive,
                        description = placesData.description,
                        latitude = placesData.latitude,
                        createdAt = placesData.createdAt,
                        longitude = placesData.longitude,
                        updatedAt = placesData.updatedAt,
                        contactNumber = placesData.contactNumber,
                        totalCapacity = placesData.totalCapacity,
                        image = placesData.image,
                        address = placesData.address,
                        operatingHours = operatingHours,
                        tariffPlans = tariffPlans
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun getPlacesRatings(
        mallId: Int,
        page: Int,
        limit: Int,
    ): Result<List<PlacesRatingModel>> {
        return when (val result = placesRepository.getPlacesRatings(
            mallId = mallId,
            page = page,
            limit = limit,
        )) {
            is Result.Success -> {
                val placesRatingData = result.data.data
                val places = placesRatingData.map {
                    Log.d("place rating data map", "trigger")
                    PlacesRatingModel(
                        id = it.id,
                        user = it.user?.let { user ->
                            UserProfileModel(
                                firstName = user.firstName,
                                address = user.address,
                                state = user.state,
                                city = user.city,
                                profilePhoto = user.profilePhoto,
                                postalCode = user.postalCode,
                                dateOfBirth = user.dateOfBirth,
                                gender = user.gender,
                                country = user.country,
                                lastName = user.lastName,
                            )
                        },
                        ratingScore = it.ratingScore,
                        reviewComment = it.reviewComment,
                    )
                }

                Result.Success(places)
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun getListParkingZone(
        placeId: Int,
    ): Result<List<ParkingZoneModel>> {
        return when (val result = placesRepository.getListParkingZone(
            placeId = placeId,
        )) {
            is Result.Success -> {
                val parkingZones = result.data.data
                val places = parkingZones.map {
                    Log.d("parking zone data map", "trigger")
                    ParkingZoneModel(
                        id = it.id,
                        isActive = it.isActive,
                        zoneName = it.zoneName,
                        zoneType = it.zoneType,
                        floorLevel = it.floorLevel,
                        totalSlots = it.totalSlots
                    )
                }

                Result.Success(places)
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun getListParkingSlot(
        zoneId: Int,
    ): Result<List<ParkingSlotModel>> {
        return when (val result = placesRepository.getListParkingSlot(
            zoneId = zoneId,
        )) {
            is Result.Success -> {
                val parkingSlots = result.data.data
                val parkingSlot = parkingSlots.map {
                    Log.d("parking slot data map", "trigger")
                    ParkingSlotModel(
                        id = it.id,
                        zoneId = it.zoneId,
                        isActive = it.isActive,
                        slotNumber = it.slotNumber,
//                        slotAvailability = it.slotAvailability,
                        slotType = it.slotType,
                        isDisabledFriendly = it.isDisabledFriendly,
                        hasEvCharger = it.hasEvCharger,
                        isOccupied = it.isOccupied,
                        isReserved = it.isReserved
                    )
                }

                Result.Success(parkingSlot)
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }
}