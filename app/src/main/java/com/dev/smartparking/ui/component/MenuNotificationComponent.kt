package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun MenuNotificationComponent(
    modifier: Modifier = Modifier,
    selectedTab: Int = 0,
    onTabSelected: (Int) -> Unit = {},
) {
    val tabs = listOf("Inbox", "Promotion")

    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTab,
        backgroundColor = Color.White,
        contentColor = Color.Blue,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                color = Color.Blue
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (selectedTab == index) Color.Blue else Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MenuNotificationComponentPreview() {
    SmartParkingTheme {
        MenuNotificationComponent()
    }
}