package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Tab
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


@Composable
fun MenuParkingAreaComponent(
    modifier: Modifier = Modifier,
    selectedTab: Int = 0,
    onTabSelected: (Int) -> Unit = {},
) {
    val menu: List<String> = listOf("A", "B", "C", "D", "E", "A", "B", "C", "D", "E")

    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = selectedTab,
        backgroundColor = Color.White,
        contentColor = Color.Blue,
        edgePadding = 0.dp, // Supaya tidak ada padding di kiri & kanan
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                color = Color.Blue
            )
        }
    ) {
        menu.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (selectedTab == index) Color.Blue else Color.Gray
                    ),
                    modifier = Modifier
                        .padding(8.dp), // Hapus fillMaxWidth agar tidak melebar ke seluruh layar
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuParkingAreaComponentPreview() {
    SmartParkingTheme {
        MenuParkingAreaComponent()
    }
}