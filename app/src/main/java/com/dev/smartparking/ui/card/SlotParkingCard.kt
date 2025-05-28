package com.dev.smartparking.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.smartparking.R
import com.dev.smartparking.domain.model.ParkingSlotModel
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun SlotParkingCard(
    modifier: Modifier = Modifier,
    available: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit,
    slot: ParkingSlotModel
) {
    Card(
        modifier = modifier
            .width(120.dp)
            .height(50.dp)
            .clickable(enabled = available) { onClick() }, // 🟢 Klik hanya jika available
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color.Blue else Color.White // 🔹 Ganti warna saat selected
        ),
        elevation = CardDefaults.elevatedCardElevation(1.dp),
        shape = RoundedCornerShape(1.dp)
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (available) {
                Text(
                    text = if (isSelected) "SELECTED" else slot.slotNumber,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) Color.White else Color.Black
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.image_car_parking1),
                    contentDescription = "",
                    modifier = Modifier
                        .size(84.dp, 35.dp)
                        .rotate(90f)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SlotParkingCardPreview() {
    SmartParkingTheme {
        SlotParkingCard(
            available = false,
            isSelected = true, // 🟢 Cek apakah slot ini terpilih
            onClick = {  },
            slot = ParkingSlotModel(
                id = 1,
                slotType = "",
                slotNumber = "",
                zoneId = 1,
                hasEvCharger = false,
                isReserved = false,
                isOccupied = true,
                isActive = false,
                isDisabledFriendly = false
            ),
        )
    }
}