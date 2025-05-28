package com.dev.smartparking.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.domain.model.UserProfileModel
import com.dev.smartparking.domain.model.VehicleModel
import com.dev.smartparking.domain.usecase.AuthUseCase
import com.dev.smartparking.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userUseCase: UserUseCase,
    private val authUseCase: AuthUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel() {
    // State
    var userVehiclesModel by mutableStateOf<VehicleModel?>(null)
        private set

    var userProfileModel by mutableStateOf<UserProfileModel?>(null)
        private set


    var errorMessageGetUserVehicle by mutableStateOf<String>("")
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var updatePasswordMessage by mutableStateOf<String?>(null)
        private set

    // UI states
    var isLoading by mutableStateOf(false)
        private set

    var isGetUserVehiclesFailed by mutableStateOf(false)
        private set

    var isGetUserProfileFailed by mutableStateOf(false)
        private set

    var isUpdateVehicleSuccess by mutableStateOf(false)
        private set

    var isUpdateVehicleFailed by mutableStateOf(false)
        private set

    var isUpdateUserPasswordFailed by mutableStateOf(false)
        private set

    var isUpdateUserPasswordSuccess by mutableStateOf(false)
        private set

    // Handler
    fun isLoadingChange(value: Boolean) {
        isLoading = value
    }

    fun onErrorMessageChange(value: String) {
        errorMessage = value
    }

    fun onIsUpdateVehicleSuccess(value: Boolean) {
        isUpdateVehicleSuccess = value
    }

    fun onIsUpdateVehicleFailed(value: Boolean) {
        isUpdateVehicleFailed = value
    }

    fun onIsUpdateUserPasswordFailed(value: Boolean) {
        isUpdateUserPasswordFailed = value
    }

    fun onisUpdateUserPasswordSuccess(value: Boolean) {
        isUpdateUserPasswordSuccess = value
    }


    fun logout() {
        viewModelScope.launch {
            val refreshToken = authPreferences.refreshToken.first()
            try {
                val result = authUseCase.logout(refreshToken = refreshToken)

                isLoading = result.isLoading()
            } catch (e: Exception) {
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
            } finally {
                isLoading = false
                authPreferences.clearUserData()
            }
        }
    }

    fun getUserVehicle(onFailed: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true

                val result = userUseCase.getVehicles()

                isLoading = result.isLoading()

                result.getOrNull()?.let { userVehicle ->
                    Log.d("user vehicle data", "$userVehicle")
                    userVehiclesModel = userVehicle[0]
                } ?: run {
                    errorMessageGetUserVehicle =
                        result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                    isGetUserVehiclesFailed = true
                }

                Log.d("user vehicles", "$userVehiclesModel")

                if (isGetUserVehiclesFailed) {
                    onFailed()
                }
            } catch (e: Exception) {
                isGetUserVehiclesFailed = true
                errorMessageGetUserVehicle = e.message ?: "Terjadi kesahalan pada sistem"
                if (isGetUserVehiclesFailed) {
                    onFailed()
                }
            }
        }
    }

    fun updateVehicles(vehicleModel: VehicleModel, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = userUseCase.updateVehicles(
                    id = vehicleModel.id,
                    licensePlate = vehicleModel.licensePlate,
                    vehicleType = vehicleModel.vehicleType,
                    brand = vehicleModel.brand,
                    color = vehicleModel.color
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let {
                    userVehiclesModel =  it
                    isUpdateVehicleSuccess = true
                } ?: run {
                    isUpdateVehicleFailed = true
                    errorMessage = result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                }

                if (isUpdateVehicleSuccess) {
                    onSuccess()
                }
            } catch (e: Exception) {
                isUpdateVehicleFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
            } finally {
                isLoading = false
            }
        }
    }

    private fun getUserProfile(onFailed: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true

                val result = userUseCase.getUserProfile()

                isLoading = result.isLoading()

                result.getOrNull()?.let { userProfile ->
                    Log.d("user profile data", "$userProfile")
                    userProfileModel = userProfile
                } ?: run {
                    errorMessage =
                        result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                    isGetUserVehiclesFailed = true
                }

                Log.d("user vehicles", "$userVehiclesModel")

                if (isGetUserProfileFailed) {
                    onFailed()
                }
            } catch (e: Exception) {
                isGetUserProfileFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
                if (isGetUserProfileFailed) {
                    onFailed()
                }
            } finally {
                isLoading = false
            }
        }
    }

    fun updateUserPassword(oldPassword: String, newPassword: String, confirmNewPassword: String, onSuccess: () -> Unit) {
        if (!validationUpdateUserPassword(oldPassword, newPassword, confirmNewPassword)) {
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true

                val result = userUseCase.updateUserPassword(
                    oldPassword = oldPassword,
                    newPassword = newPassword,
                    confirmNewPassword = confirmNewPassword
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let { response ->
                    Log.d("update user password data", "$response")
                    updatePasswordMessage = response.message
                    isUpdateUserPasswordSuccess = true
                } ?: run {
                    errorMessage =
                        result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                    isUpdateUserPasswordFailed = true
                }

                if (isUpdateUserPasswordSuccess) {
                    onSuccess()
                }
            } catch (e: Exception) {
                isUpdateUserPasswordFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
            } finally {
                isLoading = false
            }
        }
    }

    private fun validationUpdateUserPassword(oldPassword: String, newPassword: String, confirmNewPassword: String): Boolean {
        if (oldPassword.isBlank()) {
            errorMessage = "form password sebelumnya tidak boleh kosong"
            isUpdateUserPasswordFailed = true
            return false
        }

        if (newPassword.isBlank()) {
            errorMessage = "form password tidak boleh kosong"
            isUpdateUserPasswordFailed = true
            return false
        } else if (newPassword.length < 8) {
            errorMessage = "password tidak boleh kurang dari 8 karakter"
        }

        if (confirmNewPassword != newPassword) {
            errorMessage = "password dan konfirmasi password harus sama"
        }

        return true
    }

    init {
        getUserProfile {  }
    }
}