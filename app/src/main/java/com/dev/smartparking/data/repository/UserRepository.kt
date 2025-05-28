package com.dev.smartparking.data.repository

import android.util.Log
import com.dev.smartparking.data.api.ApiService
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.data.model.request.CreateVehiclesRequest
import com.dev.smartparking.data.model.request.UpdatePasswordRequest
import com.dev.smartparking.data.model.response.CreateVehiclesResponse
import com.dev.smartparking.data.model.response.GetVehiclesResponse
import com.dev.smartparking.data.model.response.ResponseData
import com.dev.smartparking.data.model.response.UpdatePasswordResponse
import com.dev.smartparking.data.model.response.UserProfileResponse
import com.dev.smartparking.data.model.response.UserResponse
import com.dev.smartparking.data.utils.ErrorUtils
import com.dev.smartparking.data.utils.Result
import com.dev.smartparking.domain.model.UserModel
import com.dev.smartparking.domain.model.VehicleModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences
) {
    suspend fun getUser(): Result<ResponseData<UserResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getUser()

                if (response.isSuccessful) {
                    response.body()?.let { userResponse ->
                        // Simpan token jika login berhasil
                        userResponse.data.let { userData ->
                            authPreferences.saveUser(
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
                        Result.Success(userResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("get user error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun getUserProfile(): Result<ResponseData<UserProfileResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getUserProfile()

                if (response.isSuccessful) {
                    response.body()?.let { userProfileResponse ->
                        // Simpan token jika login berhasil
                        userProfileResponse.data.let {}
                        Result.Success(userProfileResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("get user profile error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun updateUserPassword(
        oldPassword: String,
        newPassword: String,
        confirmNewPassword: String,
    ): Result<ResponseData<UpdatePasswordResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = UpdatePasswordRequest(
                    oldPassword = oldPassword,
                    newPassword = newPassword,
                    confirmNewPassword = confirmNewPassword
                )

                val response = apiService.updateUserPassword(request)

                if (response.isSuccessful) {
                    response.body()?.let { updatePasswordResponse ->
                        // Simpan token jika login berhasil
                        updatePasswordResponse.data.let { }
                        Result.Success(updatePasswordResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("update user password error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun createVehicles(
        licensePlate: String,
        vehicleType: String
    ): Result<ResponseData<CreateVehiclesResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = CreateVehiclesRequest(
                    licensePlate = licensePlate,
                    vehicleType = vehicleType,
                    brand = "",
                    color = "",
                    model = "",
                    rfidTag = "",
                    width = 0.0,
                    length = 0.0,
                    height = 0.0,
                    isActive = true
                )

                val response = apiService.createVehicles(request)

                if (response.isSuccessful) {
                    response.body()?.let { vehcileResponse ->
                        // Simpan token jika login berhasil
                        vehcileResponse.data.let { }
                        Result.Success(vehcileResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("create vehicles error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun getVehicles(): Result<ResponseData<List<GetVehiclesResponse>>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getUserVehicles()

                if (response.isSuccessful) {
                    response.body()?.let { vehcileResponse ->
                        // Simpan token jika login berhasil
                        vehcileResponse.data.let { }
                        Result.Success(vehcileResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("create vehicles error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun updateVehicles(
        id: Int,
        licensePlate: String,
        vehicleType: String,
        brand: String,
        color: String
    ): Result<ResponseData<GetVehiclesResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = GetVehiclesResponse(
                    id = id,
                    licensePlate = licensePlate,
                    vehicleType = vehicleType,
                    brand = brand,
                    color = color,
                    model = "",
                    rfidTag = "",
                    width = 0.0,
                    length = 0.0,
                    height = 0.0,
                    isActive = true,
                )

                val response = apiService.updateUserVehicles(
                    id = request.id,
                    updateVehiclesRequest = request
                )

                if (response.isSuccessful) {
                    response.body()?.let { vehcileResponse ->
                        // Simpan token jika login berhasil
                        vehcileResponse.data.let { }
                        Result.Success(vehcileResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("update vehicles error", "$e")
                Result.Error(e)
            }
        }
    }
}