package com.dev.smartparking.ui.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.domain.model.ParkingSlotModel
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.card.BookParkingCard
import com.dev.smartparking.ui.component.DialogAction
import com.dev.smartparking.ui.component.DialogComponent
import com.dev.smartparking.ui.component.DialogVariant
import com.dev.smartparking.ui.component.ListSlotParkingComponent
import com.dev.smartparking.ui.component.MenuParkingAreaComponent
import com.dev.smartparking.ui.component.TopBarComponent
import com.dev.smartparking.ui.element.LoadingDialog
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.ParkingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ParkingSlotScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mallId: Int,
    name: String,
    price: Int,
    nextPrice: Int
) {
    var selectedSlot by remember { mutableStateOf<ParkingSlotModel?>(null) } // 🟢 State di sini
    val parkingViewModel: ParkingViewModel = koinViewModel()
    val selectedTab by parkingViewModel.selectedTab.collectAsState()
    var areaName by remember { mutableStateOf("") }

    // Animation for the booking card
    val bookingCardHeight by animateDpAsState(
        targetValue = if (selectedSlot != null) 0.dp else (-250).dp,
        label = "Booking Card Animation"
    )

    // State for floor plan view vs list view
    var isFloorPlanView by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        parkingViewModel.getListParkingZone(mallId) {}
        parkingViewModel.getUserVehicle () {
            Log.e("get user vehicles", parkingViewModel.errorMessageGetUserVehicle)
        }
    }

    LaunchedEffect(selectedTab) {
        parkingViewModel.parkingZoneModel.getOrNull(selectedTab)?.let { zone ->
            parkingViewModel.getListParkingSlot(zone.id) {}
            areaName = zone.zoneName
        }
    }


    Scaffold(
        topBar = {
            TopBarComponent(
                title = name,
                onClickIcon = {},
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        },
        bottomBar = {
            // Only show the booking card when a slot is selected
            AnimatedVisibility(
                visible = selectedSlot != null,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                selectedSlot?.let { slot ->

                    BookParkingCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        parkingViewModel = parkingViewModel,
                        slotModel = slot,
                        price = price,
                        nextPrice = nextPrice
                    ) {
                        parkingViewModel.createBooking(mallId) {
                            navController.navigate("${Screen.Payment.route}/${parkingViewModel.createBookingModel?.id}") {
                                popUpTo(route = "${Screen.Parking.route}/$name/$mallId/$price/$nextPrice$") {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box( // 🔹 Gunakan Box untuk menempatkan BookParkingCard di bawah
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MenuParkingAreaComponent(
                    parkingViewModel = parkingViewModel,
                    selectedTab = selectedTab,
                    onTabSelected = { parkingViewModel.selectTab(it) }
                )

                ListSlotParkingComponent(
                    area = areaName,
                    selectedSlot = selectedSlot,
                    onSlotSelected = { slot ->
                        selectedSlot = if (selectedSlot?.id == slot?.id) null else slot
                        parkingViewModel.onSlotIdChange(slot?.id)
                    },
//                    onSlotSelected = { slot -> selectedSlot = if (selectedSlot == slot) null else slot },
                    parkingSlots = parkingViewModel.parkingSlotModel // 🔹 Kirim data dari ViewModel
                )

            }

            // 🔹 Pastikan BookParkingCard berada di bawah
//            selectedSlot?.let {
//                BookParkingCard(
//                    modifier = Modifier
//                        .align(Alignment.BottomCenter) // 📌 Posisi di bawah
//                        .fillMaxWidth()
//                        .padding(16.dp), // 🔹 Padding agar tidak mepet layar
//                    navController = navController,
//                    parkingViewModel = parkingViewModel,
//                )
//            }
        }
    }

    LoadingDialog(isLoading = parkingViewModel.isLoading)

    DialogComponent(
        open = parkingViewModel.isCreateBookingFailed,
        onClose = {
            parkingViewModel.onIsCreateBookingFailedChange(false)
        },
        title = "Booking",
        description = parkingViewModel.errorMessage,
        variant = DialogVariant.ERROR,
        actions = listOf(
            DialogAction(label = "Tutup", onClick = {
                parkingViewModel.onIsCreateBookingFailedChange(false)
            }),
        )
    )

    DialogComponent(
        open = parkingViewModel.isCreateBookingSuccessful,
        onClose = {
            parkingViewModel.onIsCreateBookingSuccessfulChange(false)
        },
        title = "Booking",
        description = "Berhasil Membuat Pesanan",
        variant = DialogVariant.SUCCESS,
        actions = listOf(
            DialogAction(label = "Tutup", onClick = {
                parkingViewModel.onIsCreateBookingSuccessfulChange(false)
            }),
        )
    )
}


@Preview(showBackground = true)
@Composable
private fun ParkingSlotScreenPrev() {
    SmartParkingTheme {
        ParkingSlotScreen(
            navController = rememberNavController(),
            mallId = 1,
            name = "",
            price = 1,
            nextPrice = 1
        )
    }
}