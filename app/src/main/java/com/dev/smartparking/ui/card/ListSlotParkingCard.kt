package com.dev.smartparking.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.smartparking.domain.model.ParkingSlotModel
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ListSlotParkingCard(
    modifier: Modifier = Modifier,
    area: String,
    selectedSlot: ParkingSlotModel?,
    onSlotSelected: (ParkingSlotModel?) -> Unit,
    parkingSlots: List<ParkingSlotModel> // Data slot parkir untuk setiap area
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 🟢 **Judul Area**
            Text(
                text = area,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Menggunakan data parkingSlots untuk iterasi
            parkingSlots.chunked(2).forEach { slot ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    slot.forEach { slot ->
                        SlotParkingCard(
                            available = !slot.isReserved && !slot.isOccupied,
                            isSelected = selectedSlot?.id == slot.id && selectedSlot.zoneId == slot.zoneId,
                            onClick = { onSlotSelected(slot) }, // ✅ PERBAIKI INI
                            modifier = Modifier.weight(1f),
                            slot = slot
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ListSlotParkingCardPreview() {
    SmartParkingTheme {
        ListSlotParkingCard(
            area = "A",
            selectedSlot = null,
            onSlotSelected = { },
            parkingSlots = listOf<ParkingSlotModel>()
        )
    }
}