package com.dev.smartparking.ui.element

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.DetailMallViewModel
import com.dev.smartparking.viewmodel.LocationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MallDirectionInfoElement(
    modifier: Modifier = Modifier,
    detailMallViewModel: DetailMallViewModel,
    locationViewModel: LocationViewModel
) {
    LaunchedEffect(Unit) {
        locationViewModel.fetchLocation()
    }

    val userLocation = locationViewModel.location.value
    val mall = detailMallViewModel.detailPlaceModel

    val distanceText = remember(userLocation, mall) {
        if (mall != null && userLocation != null) {
            val distance = locationViewModel.calculateDistanceTo(
                destinationLat = mall.latitude,
                destinationLon = mall.longitude
            )
            "%.1f km away".format(distance)
        } else {
            "Getting location..."
        }
    }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row() {
            DirectionElement(
                icon = Icons.Default.Directions,
                text = distanceText
            )
            DirectionElement(
                icon = Icons.Default.LocalParking,
                text = detailMallViewModel.detailPlaceModel?.totalCapacity.toString() ?: ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MallDirectionInfoElementPreview() {
    SmartParkingTheme {
        MallDirectionInfoElement(
            detailMallViewModel = koinViewModel(),
            locationViewModel = koinViewModel()
        )
    }
}