package com.dev.smartparking.domain.usecase

import com.dev.smartparking.data.repository.UserRepository
import com.dev.smartparking.data.utils.Result
import com.dev.smartparking.domain.model.LoginUser
import com.dev.smartparking.domain.model.UserModel
import com.dev.smartparking.domain.model.VehicleModel

class UserUseCase(private val userRepository: UserRepository) {
    suspend fun getUser(): Result<UserModel> {
        return when (val result = userRepository.getUser()) {
            is Result.Success -> {
                val userData = result.data.data
                Result.Success(
                    UserModel(
                        username = userData.username,
                        email = userData.email,
                        phoneNumber = userData.phoneNumber,
                        accountType = userData.accountType,
                        emailVerified = userData.emailVerified,
                        phoneVerified = userData.phoneVerified,
                        accountStatus = userData.accountStatus,
                        isVehicleActivated = userData.isVehicleActivated,
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun createVehicles(
        licensePlate: String,
        vehicleType: String
    ): Result<VehicleModel> {
        return when (val result =
            userRepository.createVehicles(licensePlate = licensePlate, vehicleType = vehicleType)) {
            is Result.Success -> {
                val vehicleData = result.data.data
                Result.Success(
                    VehicleModel(
                        id = vehicleData.id,
                        licensePlate = vehicleData.licensePlate,
                        vehicleType = vehicleData.vehicleType,
                        brand = vehicleData.brand,
                        model = vehicleData.model,
                        color = vehicleData.color,
                        rfidTag = vehicleData.rfidTag,
                        length = vehicleData.length,
                        width = vehicleData.width,
                        height = vehicleData.height,
                        isActive = vehicleData.isActive,
                        createdAt = vehicleData.createdAt,
                        updatedAt = vehicleData.updatedAt,
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }
}