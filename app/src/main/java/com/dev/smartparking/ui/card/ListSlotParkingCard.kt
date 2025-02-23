package com.dev.smartparking.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ListSlotParkingCard(
    modifier: Modifier = Modifier,
    area: String,
    selectedSlot: Pair<Int, Int>?,
    onSlotSelected: (Pair<Int, Int>) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 400.dp)
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

            repeat(5) { rowIndex ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val slot1 = Pair(area.hashCode(), rowIndex * 2)
                    val slot2 = Pair(area.hashCode(), rowIndex * 2 + 1)

                    SlotParkingCard(
                        available = true,
                        isSelected = selectedSlot == slot1,
                        onClick = { onSlotSelected(slot1) },
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(16.dp)) // Jarak antar slot

                    SlotParkingCard(
                        available = false,
                        isSelected = selectedSlot == slot2,
                        onClick = { if (false) onSlotSelected(slot2) }, // 🔹 Tidak bisa diklik jika unavailable
                        modifier = Modifier.weight(1f)
                    )
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
            onSlotSelected = { }
        )
    }
}