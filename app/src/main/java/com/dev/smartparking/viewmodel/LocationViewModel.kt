package com.dev.smartparking.viewmodel

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.location.LocationServices
import kotlin.math.*

class LocationViewModel(application: Application): AndroidViewModel(application) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    private val _location = mutableStateOf<Pair<Double, Double>?>(null)
    val location: State<Pair<Double, Double>?> = _location

    fun fetchLocation() {
        val context = getApplication<Application>()
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Sebaiknya kasih trigger state juga untuk inform permission denied
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { loc ->
                loc?.let {
                    _location.value = it.latitude to it.longitude
                }
            }
            .addOnFailureListener {
                Log.e("Location", "Error getting location: ${it.message}")
            }
    }

    fun calculateDistanceTo(
        destinationLat: Double,
        destinationLon: Double
    ): Double? {
        val currentLocation = _location.value ?: return null

        val (lat1, lon1) = currentLocation
        val earthRadius = 6371.0 // in KM

        val dLat = Math.toRadians(destinationLat - lat1)
        val dLon = Math.toRadians(destinationLon - lon1)

        val a = sin(dLat / 2).pow(2.0) +
                cos(Math.toRadians(lat1)) *
                cos(Math.toRadians(destinationLat)) *
                sin(dLon / 2).pow(2.0)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return earthRadius * c
    }
}
