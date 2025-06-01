package com.dev.smartparking.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.R.string.txt_button_book_now
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.component.DetailMallContentComponent
import com.dev.smartparking.ui.component.ErrorComponent
import com.dev.smartparking.ui.component.TopBarComponent
import com.dev.smartparking.ui.element.LoadingDialog
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.DetailMallViewModel
import com.dev.smartparking.viewmodel.LocationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailMallScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mallId: Int,
) {
    val detailMallViewModel: DetailMallViewModel = koinViewModel()
    val locationViewModel: LocationViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        detailMallViewModel.getDetailPlaces(mallId = mallId) {}
        detailMallViewModel.getPlacesRatings(mallId = mallId) {}
        Log.d("detail place", "${detailMallViewModel.detailPlaceModel}")
    }

    Scaffold(
        topBar = {
            TopBarComponent(
                title = detailMallViewModel.detailPlaceModel?.name ?: "",
                onClickIcon = {},
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) { innerPadding ->
        when (detailMallViewModel.isGetDetailPlacesSuccessful) {
            true -> {
                Box(
                    modifier = modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        DetailMallContentComponent(
                            detailMallViewModel = detailMallViewModel,
                            locationViewModel = locationViewModel
                        )
                    }

                    if (!detailMallViewModel.isClosedToday && detailMallViewModel.todayTariffRates.isNotEmpty() ) {
                        ButtonComponent(
                            text = txt_button_book_now,
                            textColor = MaterialTheme.colorScheme.background,
                            onClick = {
                                val todayTariffRate = detailMallViewModel.todayTariffRates.first()
                                navController.navigate("${Screen.Parking.route}/${detailMallViewModel.detailPlaceModel?.name}/${mallId}/${todayTariffRate.minimumCharge}/${todayTariffRate.hourlyRate}")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .align(Alignment.BottomCenter)
                        )
                    }
                }
            }

            false -> {
                ErrorComponent(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    message = detailMallViewModel.errorMessage ?: "Terjadi kesalahan",
                ) {
                    detailMallViewModel.getDetailPlaces(mallId) {}
                }
            }

            null -> {}
        }
    }

    LoadingDialog(detailMallViewModel.isLoading)
}

@Preview(showBackground = true)
@Composable
private fun DetailMallScreenPreview() {
    SmartParkingTheme {
        DetailMallScreen(navController = rememberNavController(), mallId = 1)
    }
}