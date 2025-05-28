package com.dev.smartparking.data.repository

import android.util.Log
import com.dev.smartparking.data.api.ApiService
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.data.model.response.ParkingSlotResponse
import com.dev.smartparking.data.model.response.ParkingZoneResponse
import com.dev.smartparking.data.model.response.PlacesRatingResponse
import com.dev.smartparking.data.model.response.PlacesResponse
import com.dev.smartparking.data.model.response.ResponseData
import com.dev.smartparking.data.utils.ErrorUtils
import com.dev.smartparking.data.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PlacesRepository(private val apiService: ApiService, authPreferences: AuthPreferences) {
    suspend fun getPlaces(page: Int, limit: Int, search: String = "", city: String = "", area: String = "" ): Result<ResponseData<List<PlacesResponse>>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPlaces(
                    page = page,
                    limit = limit,
                    search = search,
                    city = city,
                    area = area
                )
                if (response.isSuccessful) {
                    response.body()?.let { placesResponse ->
                        // Simpan token jika login berhasil
                        placesResponse.data.let {}
                        Result.Success(placesResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("get places error", "$e")
                Result.Error(e)
            }
        }
    }


    suspend fun getDetailPlaces(mallId: Int ): Result<ResponseData<PlacesResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDetailPlaces(mallId = mallId)
                if (response.isSuccessful) {
                    response.body()?.let { placesResponse ->
                        // Simpan token jika login berhasil
                        placesResponse.data.let {}
                        Result.Success(placesResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("get detail places error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun getPlacesRatings(mallId: Int, page: Int, limit: Int ): Result<ResponseData<List<PlacesRatingResponse>>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPlacesRatings(
                    mallId = mallId,
                    page = page,
                    limit = limit,
                )
                if (response.isSuccessful) {
                    response.body()?.let { placesRatingsResponse ->
                        // Simpan token jika login berhasil
                        placesRatingsResponse.data.let {}
                        Result.Success(placesRatingsResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("get places ratings error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun getListParkingZone(placeId: Int ): Result<ResponseData<List<ParkingZoneResponse>>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getListParkingZone(
                    placeId = placeId,
                )
                if (response.isSuccessful) {
                    response.body()?.let { parkingZoneResponse ->
                        // Simpan token jika login berhasil
                        parkingZoneResponse.data.let {}
                        Result.Success(parkingZoneResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("get list parking zone error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun getListParkingSlot(zoneId: Int ): Result<ResponseData<List<ParkingSlotResponse>>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getListParkingSlot(
                    zoneId = zoneId,
                )
                if (response.isSuccessful) {
                    response.body()?.let { parkingSlotResponse ->
                        // Simpan token jika login berhasil
                        parkingSlotResponse.data.let {}
                        Result.Success(parkingSlotResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("get list parking slot error", "$e")
                Result.Error(e)
            }
        }
    }
}