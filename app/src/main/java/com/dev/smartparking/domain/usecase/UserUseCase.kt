package com.dev.smartparking.domain.usecase

import com.dev.smartparking.data.repository.UserRepository
import com.dev.smartparking.data.utils.Result
import com.dev.smartparking.domain.model.UpdatePasswordModel
import com.dev.smartparking.domain.model.UserModel
import com.dev.smartparking.domain.model.UserProfileModel
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

    suspend fun getVehicles(): Result<List<VehicleModel>> {
        return when (val result = userRepository.getVehicles()) {
            is Result.Success -> {
                val vehicleData = result.data.data
                val vehicles = vehicleData.map {
                    VehicleModel(
                        id = it.id,
                        licensePlate = it.licensePlate,
                        vehicleType = it.vehicleType,
                        brand = it.brand,
                        model = it.model,
                        color = it.color,
                        rfidTag = it.rfidTag,
                        length = it.length,
                        width = it.width,
                        height = it.height,
                        isActive = it.isActive,
                        createdAt = it.createdAt,
                        updatedAt = it.updatedAt
                    )
                }
                Result.Success(vehicles)
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun updateVehicles(
        id: Int,
        licensePlate: String,
        vehicleType: String,
        brand: String,
        color: String
    ): Result<VehicleModel> {
        return when (val result =
            userRepository.updateVehicles(
                id = id,
                licensePlate = licensePlate,
                vehicleType = vehicleType,
                brand = brand,
                color = color
            )) {
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

    suspend fun getUserProfile(): Result<UserProfileModel> {
        return when (val result = userRepository.getUserProfile()) {
            is Result.Success -> {
                val vehicleData = result.data.data
                Result.Success(UserProfileModel(
                    firstName = vehicleData.firstName,
                    lastName = vehicleData.lastName,
                    profilePhoto = vehicleData.profilePhoto,
                    gender = vehicleData.gender,
                    dateOfBirth = vehicleData.dateOfBirth,
                    address = vehicleData.address,
                    city = vehicleData.city,
                    state = vehicleData.state,
                    postalCode = vehicleData.postalCode,
                    country = vehicleData.country
                ))
            }
            is Result.Error -> {
                Result.Error(result.exception)
            }
            Result.Loading -> {
                Result.Loading
            }
        }
    }

    suspend fun updateUserPassword(
        oldPassword: String,
        newPassword: String,
        confirmNewPassword: String,
    ): Result<UpdatePasswordModel> {
        return when (val result =
            userRepository.updateUserPassword(
                oldPassword = oldPassword,
                newPassword = newPassword,
                confirmNewPassword = confirmNewPassword
            )) {
            is Result.Success -> {
                val updatePasswordData = result.data.data
                Result.Success(
                    UpdatePasswordModel(
                        success = updatePasswordData.success,
                        message = updatePasswordData.message
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }
}