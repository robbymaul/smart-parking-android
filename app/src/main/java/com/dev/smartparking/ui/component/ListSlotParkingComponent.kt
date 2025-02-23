package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.card.ListSlotParkingCard
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ListSlotParkingComponent(
    modifier: Modifier = Modifier,
    selectedSlot: Pair<Int, Int>?, // 🟢 Tambahkan parameter state
    onSlotSelected: (Pair<Int, Int>) -> Unit // 🔹 Callback agar bisa mengupdate slot
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(count = 10) { index ->
            ListSlotParkingCard(
                area = "A$index",
                selectedSlot = selectedSlot,
                onSlotSelected = onSlotSelected // 🔹 Kirim callback ke bawah
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ListSlotParkingComponentPreview() {
    SmartParkingTheme {
        ListSlotParkingComponent(
            selectedSlot = null,
            onSlotSelected = {},
        )
    }
}