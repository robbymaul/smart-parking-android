package com.dev.smartparking.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.smartparking.domain.model.PlacesModel
import com.dev.smartparking.domain.usecase.PlacesUseCase
import kotlinx.coroutines.launch

class HomepageViewModel(private val placesUseCase: PlacesUseCase) : ViewModel() {
    // states
    var placesModel by mutableStateOf<MutableList<PlacesModel>>(mutableListOf())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var pageGetPlace by mutableStateOf(1)

    var limitGetPlace by mutableStateOf(10)
        private set

    // UI states
    var isLoading by mutableStateOf(false)
        private set

    var isGetPlacesFailed by mutableStateOf(false)
        private set

    var isGetDetailPlacesFailed by mutableStateOf(false)
        private set

    var isGetDetailPlacesSuccessful by mutableStateOf(false)
        private set

    // handler
    fun onPlacesModelChange(value: MutableList<PlacesModel>) {
        placesModel = value
    }

    fun addToPlaceModelList(value: PlacesModel) {
        placesModel.add(value)
    }

    fun onChangePageGetPlace(value: Int) {
        pageGetPlace = value
    }

    fun onChangeLimitGetPlace(value: Int) {
        limitGetPlace = value
    }

    fun isGetDetailPlacesSuccessfulChange(value: Boolean) {
        isGetDetailPlacesSuccessful = value
    }

    fun isGetDetailPlacesFailedChange(value: Boolean) {
        isGetDetailPlacesFailed = value
    }
    
    // validation

    // fetch
    fun getPlaces(onFailed: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = placesUseCase.getPlaces(
                    page = pageGetPlace,
                    limit = limitGetPlace
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let { placeData ->
                    Log.d("place data", "$placeData")
                    if (placesModel.isEmpty()) {
                        placesModel = placeData.toMutableList()
                    } else {
                        placesModel.addAll(placeData)
                    }
                } ?: run {
                    errorMessage =
                        result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                    isGetPlacesFailed = true
                }

                if (isGetPlacesFailed) {
                    onFailed()
                }
            } catch (e: Exception) {
                isGetPlacesFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
                if (isGetPlacesFailed) {
                    onFailed()
                }
            }
        }
    }

    init {
        getPlaces {  }
    }
}