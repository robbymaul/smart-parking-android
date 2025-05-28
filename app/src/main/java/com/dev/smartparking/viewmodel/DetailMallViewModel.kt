package com.dev.smartparking.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.data.repository.AuthRepository
import com.dev.smartparking.domain.model.OperatingHourModel
import com.dev.smartparking.domain.model.PlacesModel
import com.dev.smartparking.domain.model.PlacesRatingModel
import com.dev.smartparking.domain.model.TariffRateModel
import com.dev.smartparking.domain.usecase.PlacesUseCase
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate

class DetailMallViewModel(private val placesUseCase: PlacesUseCase, authPreferences: AuthPreferences): ViewModel() {

    // states
    var detailPlaceModel by mutableStateOf<PlacesModel?>(null)
        private set

    var placesRatingModel by mutableStateOf<MutableList<PlacesRatingModel>>(mutableListOf())
        private set

    var page by mutableStateOf(1)
        private set

    var limit by mutableStateOf(5)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var errorMessageGetPlacesRatings by mutableStateOf<String?>(null)
        private set


    // UI states
    var isLoading by mutableStateOf(false)
        private set

    var isGetDetailPlacesFailed by mutableStateOf(false)
        private set

    var isGetDetailPlacesSuccessful by mutableStateOf<Boolean?>(null)
        private set

    var isGetPlacesRatingsSuccessful by mutableStateOf<Boolean?>(null)
        private set

    var isGetPlacesRatingsFailed by mutableStateOf<Boolean>(false)
        private set

    val todayTariffRates: List<TariffRateModel>
        get() {
            val todayCategory = when (LocalDate.now().dayOfWeek) {
                DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> "Weekend"
                else -> "Weekday"
            }

            return detailPlaceModel?.tariffPlans
                ?.firstOrNull { it.isActive } // ambil plan aktif
                ?.tariffRates
                ?.filter { it.dayCategory.equals(todayCategory, ignoreCase = true) }
                ?: emptyList()
        }



    val isClosedToday: Boolean
        get() {
            val today = LocalDate.now().dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
            return detailPlaceModel?.operatingHours
                ?.firstOrNull { it.dayOfWeek.equals(today, ignoreCase = true) }
                ?.isClosed == true
        }

    val operatingHourToday: OperatingHourModel?
        get() {
            val today = LocalDate.now().dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
            return detailPlaceModel?.operatingHours
                ?.firstOrNull { it.dayOfWeek.equals(today, ignoreCase = true) }
        }

    // handler
    fun isGetDetailPlacesSuccessfulChange(value: Boolean) {
        isGetDetailPlacesSuccessful = value
    }

    fun isGetDetailPlacesFailedChange(value: Boolean) {
        isGetDetailPlacesFailed = value
    }


    // fetch
    fun getDetailPlaces(mallId: Int, onFailed: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = placesUseCase.getDetailPlaces(mallId = mallId)

                isLoading = result.isLoading()

                result.getOrNull()?.let { placeData ->
                    Log.d("place data", "$placeData")
                    detailPlaceModel = placeData
                    isGetDetailPlacesSuccessful = true
                } ?: run {
                    errorMessage =
                        result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                    isGetDetailPlacesFailed = true
                }

                if (isGetDetailPlacesFailed) {
                    onFailed()
                }
            } catch (e: Exception) {
                isGetDetailPlacesFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
                if (isGetDetailPlacesFailed) {
                    onFailed()
                }
            } finally {
                isLoading = false
            }
        }
    }

    fun getPlacesRatings(mallId: Int, onFailed: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = placesUseCase.getPlacesRatings(
                    mallId = mallId,
                    page = page,
                    limit = limit
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let { placeRatingData ->
                    Log.d("place rating data", "$placeRatingData")
                    if (placesRatingModel.isEmpty()) {
                        placesRatingModel = placesRatingModel.toMutableList()
                    } else {
                        placesRatingModel.addAll(placeRatingData)
                    }
                } ?: run {
                    errorMessageGetPlacesRatings =
                        result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                    isGetPlacesRatingsFailed = true
                }

                if (isGetPlacesRatingsFailed) {
                    onFailed()
                }
            } catch (e: Exception) {
                isGetPlacesRatingsFailed = true
                errorMessageGetPlacesRatings = e.message ?: "Terjadi kesahalan pada sistem"
                if (isGetPlacesRatingsFailed) {
                    onFailed()
                }
            }
        }
    }
}