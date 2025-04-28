package com.dev.smartparking.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.smartparking.data.STNKActivationData
import com.dev.smartparking.domain.usecase.UserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class STNKActivationViewModel(private val userUseCase: UserUseCase): ViewModel() {
    // states
    var licensePlate by mutableStateOf("")
        private set

    var vehicleType by mutableStateOf("car")
        private set

    // ui states
    var isLoading by mutableStateOf(false)
        private set

    var isCreateVehicleSuccessful by mutableStateOf(false)
        private set

    var isCreateVehicleFailed by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    // handler
    fun onLicensePlateChange(value: String) {
        licensePlate = value
    }

    fun onVehicleTypeChange(value: String) {
        vehicleType = value
    }

    fun isCreateVehicleSuccessfulChange(value: Boolean) {
        isCreateVehicleSuccessful = value
    }

    fun isCreateVehicleFailedChange(value: Boolean) {
        isCreateVehicleFailed = value
    }

    // validation
    fun isValid(): Boolean {
        val cleaned = licensePlate.filter { !it.isWhitespace() }.uppercase()
        val regex = Regex("^([A-Z])([A-Z0-9]?)([0-9]{1,4})([A-Z]{0,4})$")
        return regex.matches(cleaned)
    }

    private fun validationCreateVehicle(): Boolean {
        if (!isValid()) {
            errorMessage = "data tidak valid"
            isCreateVehicleFailed = true
            return false
        }

        if (licensePlate.isBlank()) {
            errorMessage = "nomor kendaraan tidak boleh kosong"
            isCreateVehicleFailed = true
            return false
        }

        if (vehicleType.isBlank()) {
            errorMessage = "tipe kendaraan salah"
            isCreateVehicleFailed = true
            return false
        }

        return true
    }

    // fetch
    fun createVehicle(onSuccess: ()-> Unit) {
        if (!validationCreateVehicle()) {
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = userUseCase.createVehicles(
                    licensePlate = licensePlate ,
                    vehicleType = vehicleType
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let {
                    isCreateVehicleSuccessful = true
                } ?: run {
                    isCreateVehicleFailed = true
                    errorMessage = result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                }

                if (isCreateVehicleSuccessful) {
                    onSuccess()
                }
            } catch (e: Exception) {
                isCreateVehicleFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
            } finally {
                isLoading = false
            }
        }
    }

    private fun formatSTNK(raw: String): String {
        val cleaned = raw.filter { !it.isWhitespace() }.uppercase()
        // Regex untuk mencocokkan format:
        // Group 1: Karakter pertama (wajib huruf)
        // Group 2: Karakter kedua (opsional, huruf atau digit)
        // Group 3: 1 sampai 4 digit
        // Group 4: 0 sampai 4 huruf (opsional)
        val regex = Regex("^([A-Z])([A-Z0-9]?)([0-9]{1,4})([A-Z]{0,4})$")
        val match = regex.find(cleaned)

        return if (match != null) {
            // Ambil group values
            val prefix = match.groupValues[1] + match.groupValues[2]
            val digits = match.groupValues[3]
            val suffix = match.groupValues[4]
            if (suffix.isNotEmpty()) {
                "$prefix $digits $suffix"
            } else {
                "$prefix $digits"
            }
        } else {
            // Jika format tidak sesuai, kembalikan string asli atau pesan error
            raw
        }
    }

    private fun resetStates() {
        // state
        licensePlate = ""

        // UI states
        isLoading = false
        errorMessage = null
        isCreateVehicleSuccessful = false
        isCreateVehicleFailed = false
    }
}