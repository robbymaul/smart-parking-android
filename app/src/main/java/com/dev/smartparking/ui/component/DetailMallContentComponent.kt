package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.activity.RatePriceHourCard
import com.dev.smartparking.activity.ReviewsCard
import com.dev.smartparking.ui.element.HeadingMallElement
import com.dev.smartparking.ui.element.MallDirectionButtonElement
import com.dev.smartparking.ui.element.MallDirectionInfoElement
import com.dev.smartparking.ui.section.ContentSection
import com.dev.smartparking.ui.section.ExpandableTextSection
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun DetailMallContentComponent(
    modifier: Modifier = Modifier
) {
    LazyColumn {
        item {
            HeadingMallElement()
        }
        item {
            MallDirectionInfoElement()
        }
        item {
            MallDirectionButtonElement()
        }
        item {
            ExpandableTextSection(
                title = R.string.title_screen_login,
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
            )
        }
        item {
            ContentSection(
                modifier = Modifier.padding(8.dp),
                title = R.string.txt_login,
            ) {
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row (
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.DirectionsCar,
                                contentDescription = "",
                                tint = Color.Blue
                            )
                            Text(
                                text = "Vehicle Type"
                            )
                        }
                        Text(
                            text = "Car"
                        )
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        RatePriceHourCard()
                        RatePriceHourCard()
                    }
                }
            }
        }
        item {
            ContentSection(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),
                title = R.string.txt_button_login1
            ) {
                repeat(10) {
                    ReviewsCard(
                        modifier = modifier.fillMaxWidth(),
                        rating = 3
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailMallContentComponentPreview() {
    SmartParkingTheme {
        DetailMallContentComponent()
    }
}