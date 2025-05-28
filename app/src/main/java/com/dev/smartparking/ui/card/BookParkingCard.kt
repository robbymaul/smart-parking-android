package com.dev.smartparking.ui.card

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Accessible
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.EvStation
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.smartparking.domain.model.ParkingSlotModel
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.ParkingViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookParkingCard(
    modifier: Modifier = Modifier,
    parkingViewModel: ParkingViewModel,
    slotModel: ParkingSlotModel,
    price: Int,
    nextPrice: Int,
    onClick: () -> Unit
) {
    val timeFormatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
    val dateFormatter = remember { SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault()) }
    val calendar = remember { Calendar.getInstance() }

    // Start Time (current time)
    val startTime = remember { calendar.clone() as Calendar }

    // Selected date (default to today)
    var selectedDate by remember { mutableStateOf(calendar.time) }

    val isoFormatter = remember {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
    }

    // Calculate end time based on duration
    val selectedEndTime = remember {
        derivedStateOf {
            val endTime = (calendar.clone() as Calendar).apply {
                time = selectedDate
                add(Calendar.HOUR_OF_DAY, parkingViewModel.selectedDuration)
            }
            timeFormatter.format(endTime.time)
        }
    }

    // Calculate price
    val pricePerHour = price.toFloat() // pastikan price juga Float
    val totalPrice = remember {
        derivedStateOf {
            val multiplier = if (slotModel.hasEvCharger) 1.5f else 1f
            val additionalPrice =
                if (parkingViewModel.selectedDuration == 1) 0f else parkingViewModel.selectedDuration * nextPrice * multiplier
            (pricePerHour + additionalPrice).toInt()
        }
    }

    // Assign Scheduled Entry and Scheduled Exit
    LaunchedEffect(parkingViewModel.selectedDuration) {
        Log.d("selected duration launc effect", "trigger selected duration launc effect")
        val now = Calendar.getInstance()

        // Entry adalah sekarang
        val entry = now.time

        // Exit = entry + durasi
        val exit = (now.clone() as Calendar).apply {
            add(Calendar.HOUR_OF_DAY, parkingViewModel.selectedDuration)
        }.time

        // Format ke ISO 8601 (Z artinya UTC)
        val isoFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("Asia/Jakarta") // pakai zona waktu Jakarta (WIB)
        }

        parkingViewModel.onScheduledEntryChange(isoFormatter.format(entry))
        parkingViewModel.onScheduledExitChange(isoFormatter.format(exit))
    }


    // State for bottom sheet

    // Duration Bottom Sheet
    if (parkingViewModel.showDurationSheet) {
        ModalBottomSheet(
            onDismissRequest = { parkingViewModel.onShowDurationSheetChange(false) },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Select Booking Duration",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Duration options (1-6 hours)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(6) { hour ->
                        val duration = hour + 1
                        ElevatedButton(
                            onClick = {
                                parkingViewModel.onSelectedDurationChange(duration)
                                parkingViewModel.onShowDurationSheetChange(false)
                            },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = if (parkingViewModel.selectedDuration == duration)
                                    MaterialTheme.colorScheme.primaryContainer
                                else MaterialTheme.colorScheme.surface
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "$duration Hour${if (duration > 1) "s" else ""}",
                                color = if (parkingViewModel.selectedDuration == duration)
                                    MaterialTheme.colorScheme.onPrimaryContainer
                                else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }

    // Date Picker Dialog
    if (parkingViewModel.showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDate.time
        )

        DatePickerDialog(
            onDismissRequest = { parkingViewModel.onShowDatePickerChange(false) },
            confirmButton = {
                Button(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        val newDate = Calendar.getInstance().apply {
                            timeInMillis = it
                            set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY))
                            set(Calendar.MINUTE, calendar.get(Calendar.MINUTE))
                        }
                        selectedDate = newDate.time
                    }
                    parkingViewModel.onShowDatePickerChange(false)
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { parkingViewModel.onShowDatePickerChange(false) }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    // Main Booking Card
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header with slot info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Slot ${slotModel.slotNumber}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    // Features badges
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (slotModel.isDisabledFriendly) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Accessible,
                                contentDescription = "Disabled Friendly",
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        if (slotModel.hasEvCharger) {
                            Icon(
                                imageVector = Icons.Default.EvStation,
                                contentDescription = "EV Charger",
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                // Date selection
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                            RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Date",
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = dateFormatter.format(selectedDate),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Time selection
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Start Time
                Column {
                    Text(
                        text = "Start Time",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = timeFormatter.format(startTime.time),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Duration selector
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Duration",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "${parkingViewModel.selectedDuration} hour${if (parkingViewModel.selectedDuration > 1) "s" else ""}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .clickable { parkingViewModel.onShowDurationSheetChange(true) }
                            .background(
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                                RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }

                // End Time
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "End Time",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = selectedEndTime.value,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Price and Book button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Price
                Column {
                    Text(
                        text = "Total Price",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "Rp ${
                            NumberFormat.getNumberInstance(Locale.getDefault())
                                .format(totalPrice.value)
                        }",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    // Show EV surcharge if applicable
                    if (slotModel.hasEvCharger) {
                        Text(
                            text = "Includes EV charging",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }

                // Book button
                Button(
                    onClick = onClick,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .height(48.dp)
                        .width(120.dp)
                ) {
                    Text(
                        text = "Book Now",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun BookParkingCardPreview() {
    SmartParkingTheme {
        BookParkingCard(
            parkingViewModel = koinViewModel(),
            slotModel = ParkingSlotModel(
                hasEvCharger = true,
                isActive = true,
                isReserved = true,
                isOccupied = true,
                id = 1,
                slotNumber = "",
                slotType = "",
                zoneId = 1,
                isDisabledFriendly = true
            ),
            price = 1,
            nextPrice = 1
        ) {}
    }
}