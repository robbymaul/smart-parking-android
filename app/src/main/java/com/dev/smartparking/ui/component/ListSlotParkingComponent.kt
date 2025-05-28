package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.domain.model.ParkingSlotModel
import com.dev.smartparking.ui.card.ListSlotParkingCard
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ListSlotParkingComponent(
    modifier: Modifier = Modifier,
    selectedSlot: ParkingSlotModel?,
    onSlotSelected: (ParkingSlotModel?) -> Unit,
    parkingSlots: List<ParkingSlotModel>, // 🔹 Terima data parking slot dari luar
    area: String
) {
    ListSlotParkingCard(
        modifier = modifier,
        area = area, // atau informasi lain yang sesuai
        selectedSlot = selectedSlot,
        onSlotSelected = { slot -> // slot now refers to Pair<Int, Int>
            onSlotSelected(slot) // Pass the slot directly
        },
        parkingSlots = parkingSlots
    )
}




@Preview(showBackground = true)
@Composable
private fun ListSlotParkingComponentPreview() {
    SmartParkingTheme {
        ListSlotParkingComponent(
            selectedSlot = null,
            onSlotSelected = {},
            parkingSlots = listOf<ParkingSlotModel>(),
            area = ""
        )
    }
}