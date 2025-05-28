package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.ParkingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MenuParkingAreaComponent(
    modifier: Modifier = Modifier,
    selectedTab: Int = 0,
    onTabSelected: (Int) -> Unit = {},
    parkingViewModel: ParkingViewModel,
) {
    val parkingZoneList = parkingViewModel.parkingZoneModel

    if (parkingZoneList.isEmpty()) {
        Text("No parking zones available", modifier = Modifier.padding(16.dp))
        return
    }

    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = selectedTab,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            if (tabPositions.size > selectedTab) {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = Color.Blue
                )
            }
        },
        containerColor = Color.White
    ) {
        parkingZoneList.forEachIndexed { index, zone ->
            Tab(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = zone.zoneName,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (selectedTab == index) Color.Blue else Color.Gray
                        ),
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
private fun MenuParkingAreaComponentPreview() {
    SmartParkingTheme {
        MenuParkingAreaComponent(parkingViewModel = koinViewModel())
    }
}