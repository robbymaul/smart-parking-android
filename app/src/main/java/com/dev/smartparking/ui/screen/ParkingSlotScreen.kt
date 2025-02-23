package com.dev.smartparking.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.card.BookParkingCard
import com.dev.smartparking.ui.component.ListSlotParkingComponent
import com.dev.smartparking.ui.component.MenuParkingAreaComponent
import com.dev.smartparking.ui.component.TopBarMenuHomepageComponent
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ParkingSlotScreen(modifier: Modifier = Modifier) {
    var selectedSlot by remember { mutableStateOf<Pair<Int, Int>?>(null) } // 🟢 State di sini

    Scaffold (
        topBar = { TopBarMenuHomepageComponent() }
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
                MenuParkingAreaComponent()

                ListSlotParkingComponent(
                    selectedSlot = selectedSlot,
                    onSlotSelected = { slot ->
                        selectedSlot = if (selectedSlot == slot) null else slot
                    }
                )
            }

            // 🔹 Pastikan BookParkingCard berada di bawah
            selectedSlot?.let {
                BookParkingCard(
                    modifier = Modifier
                        .align(Alignment.BottomCenter) // 📌 Posisi di bawah
                        .fillMaxWidth()
                        .padding(16.dp) // 🔹 Padding agar tidak mepet layar
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ParkingSlotScreenPrev() {
    SmartParkingTheme {
        ParkingSlotScreen()
    }
}