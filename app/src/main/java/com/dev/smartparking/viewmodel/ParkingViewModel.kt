package com.dev.smartparking.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.smartparking.domain.model.CreateBooking
import com.dev.smartparking.domain.model.ParkingSlotModel
import com.dev.smartparking.domain.model.ParkingZoneModel
import com.dev.smartparking.domain.model.VehicleModel
import com.dev.smartparking.domain.usecase.BookingUseCase
import com.dev.smartparking.domain.usecase.PlacesUseCase
import com.dev.smartparking.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ParkingViewModel(private val userUseCase: UserUseCase, private val placesUseCase: PlacesUseCase, private val bookingUseCase: BookingUseCase) : ViewModel() {
    // states
    var parkingZoneModel by mutableStateOf<List<ParkingZoneModel>>(listOf())
        private set

    var parkingSlotModel by mutableStateOf<List<ParkingSlotModel>>(listOf())
        private set

    var userVehiclesModel by mutableStateOf<List<VehicleModel>>(listOf())
        private set

    var createBookingModel by mutableStateOf<CreateBooking?>(null)
        private set

    var slotId by mutableStateOf<Int?>(0)
        private set

    var scheduledEntry by mutableStateOf<String>("")
        private set

    var scheduledExit by mutableStateOf<String>("")
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var errorMessageGetUserVehicle by mutableStateOf<String>("")
        private set

    var selectedParkingZone by mutableStateOf<ParkingZoneModel?>(null)
        private set

    var selectedDuration by  mutableStateOf(1)  // Default 1 hour
        private set

    // UI States
    var isLoading by mutableStateOf(false)
        private set

    var isGetListParkingZoneFailed by mutableStateOf(false)
        private set

    var isGetListParkingSlotFailed by mutableStateOf(false)
        private set

    var isGetUserVehiclesFailed by mutableStateOf(false)
        private set

    var isCreateBookingSuccessful by mutableStateOf(false)
        private set

    var isCreateBookingFailed by mutableStateOf(false)
        private set


    var showDurationSheet by mutableStateOf(false)
        private set

    var showDatePicker by mutableStateOf(false)
        private set

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab

    fun selectTab(index: Int) {
        _selectedTab.value = index
        selectedParkingZone = parkingZoneModel[_selectedTab.value]
    }

    // handler
    fun onSelectedDurationChange(value: Int) {
        selectedDuration = value
    }

    fun onShowDurationSheetChange(value: Boolean) {
        showDurationSheet = value
    }

    fun onShowDatePickerChange(value: Boolean) {
        showDatePicker = value
    }

    fun onSlotIdChange(value: Int?) {
        slotId = value
    }

    fun onScheduledEntryChange(value: String) {
        scheduledEntry = value
    }

    fun onScheduledExitChange(value: String) {
        scheduledExit = value
    }

    fun onIsCreateBookingFailedChange(value: Boolean) {
        isCreateBookingFailed = value
    }

    fun onIsCreateBookingSuccessfulChange(value: Boolean) {
        isCreateBookingSuccessful = value
    }

    // validation

    // fetch
    fun getListParkingZone(placeId: Int, onFailed: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = placesUseCase.getListParkingZone(
                    placeId = placeId,
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let { parkingZone ->
                    Log.d("parking zone data", "$parkingZone")
                    parkingZoneModel = parkingZone
                } ?: run {
                    errorMessage =
                        result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                    isGetListParkingZoneFailed = true
                }

                if (isGetListParkingZoneFailed) {
                    onFailed()
                }
            } catch (e: Exception) {
                isGetListParkingZoneFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
                if (isGetListParkingZoneFailed) {
                    onFailed()
                }
            }
        }
    }

    fun getListParkingSlot(zoneId: Int, onFailed: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = placesUseCase.getListParkingSlot(
                    zoneId = zoneId,
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let { parkingSlot ->
                    Log.d("parking slot data", "$parkingSlot")
                    parkingSlotModel = parkingSlot
                } ?: run {
                    errorMessage =
                        result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                    isGetListParkingSlotFailed = true
                }

                if (isGetListParkingSlotFailed) {
                    onFailed()
                }
            } catch (e: Exception) {
                isGetListParkingSlotFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
                if (isGetListParkingSlotFailed) {
                    onFailed()
                }
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
                    userVehiclesModel = userVehicle
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

    fun createBooking(mallId: Int, onSuccess: () -> Unit) {
        if (!validationInputCreateBooking()) {
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = bookingUseCase.createBooking(
                    vehicleId = userVehiclesModel[0].id,
                    slotId = slotId ?: 0,
                    placeId = mallId,
                    scheduledEntry = scheduledEntry,
                    scheduledExit = scheduledExit,
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let {
                    createBookingModel =  it
                    isCreateBookingSuccessful = true
                } ?: run {
                    isCreateBookingFailed = true
                    errorMessage = result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                }

                if (isCreateBookingSuccessful) {
                    resetStates()
                    onSuccess()
                }
            } catch (e: Exception) {
                isCreateBookingFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
            } finally {
                slotId = 0
                isLoading = false
            }
        }
    }

    private fun validationInputCreateBooking(): Boolean {
        val validations = listOf(
            slotId to "Please select a parking slot.",
        )

        for ((value, message) in validations) {
            if (value == null) {
                setErrorCreateBooking(message)
                return false
            }
        }

        val stringValidations = listOf(
            scheduledEntry to "Please select your entry time.",
            scheduledExit to "Please select your exit time."
        )

        for ((value, message) in stringValidations) {
            if (value.isBlank()) {
                setErrorCreateBooking(message)
                return false
            }
        }

        return true
    }

    private fun setErrorCreateBooking(message: String) {
        errorMessage = message
        isCreateBookingFailed = true
    }

    private fun resetStates() {
        // states
        parkingZoneModel = listOf()
        parkingSlotModel = listOf()
        userVehiclesModel = listOf()
        slotId  = 0
        scheduledEntry = ""
        scheduledExit = ""
        errorMessage = null
        errorMessageGetUserVehicle = ""
        selectedParkingZone = null
        selectedDuration  = 1

        // UI state
        isLoading = false
        isGetListParkingZoneFailed = false
        isGetListParkingSlotFailed = false
        isGetUserVehiclesFailed = false
        isCreateBookingSuccessful = false
        isCreateBookingFailed = false
        showDurationSheet = false
        showDatePicker = false
    }
}