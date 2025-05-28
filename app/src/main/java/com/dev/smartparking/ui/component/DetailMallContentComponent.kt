package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.activity.RatePriceHourCard
import com.dev.smartparking.activity.ReviewsCard
import com.dev.smartparking.ui.card.ImageMallDetailCard
import com.dev.smartparking.ui.element.HeadingMallElement
import com.dev.smartparking.ui.element.MallDirectionButtonElement
import com.dev.smartparking.ui.element.MallDirectionInfoElement
import com.dev.smartparking.ui.section.ContentSection
import com.dev.smartparking.ui.section.ExpandableTextSection
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.DetailMallViewModel
import com.dev.smartparking.viewmodel.LocationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailMallContentComponent(
    modifier: Modifier = Modifier,
    detailMallViewModel: DetailMallViewModel,
    locationViewModel: LocationViewModel
) {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        item {
            ImageMallDetailCard(
                imageUrl = "https://res.cloudinary.com/dxdtxld4f/image/upload/v1738768682/skripsi/image_mall1_ixzb6u.jpg",
            )
        }
        item {
            HeadingMallElement(detailMallViewModel = detailMallViewModel)
        }
        item {
            MallDirectionInfoElement(detailMallViewModel = detailMallViewModel, locationViewModel = locationViewModel)
        }
        item {
            MallDirectionButtonElement(detailMallViewModel = detailMallViewModel)
        }
        item {
            ExpandableTextSection(
                title = R.string.txt_title_info1,
                content = detailMallViewModel.detailPlaceModel?.description ?: ""
            )
        }
        item {
            ContentSection(
                modifier = Modifier.padding(8.dp),
                title = R.string.txt_title_parking_time1,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        detailMallViewModel.todayTariffRates.firstOrNull()?.let { rate ->
                            RatePriceHourCard(
                                duration = "0 - 1 Hour",
                                price = rate.minimumCharge
                            )
                            RatePriceHourCard(
                                duration = "After 1 Hour",
                                price = rate.hourlyRate,
                                cardColor = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
        item {
            ContentSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .heightIn(min = 160.dp),
                title = R.string.txt_title_review1,
            ) {
                when {
                    detailMallViewModel.placesRatingModel.isEmpty() -> {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Belum ada ulasan",
                            textAlign = TextAlign.Center
                        )
                    }

                    else -> {
                        detailMallViewModel.placesRatingModel.forEach {
                            ReviewsCard(
                                modifier = modifier.fillMaxWidth(),
                                placesRatingModel = it
                            )
                        }
                    }
                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailMallContentComponentPreview() {
    SmartParkingTheme {
        DetailMallContentComponent(
            detailMallViewModel = koinViewModel(),
            locationViewModel = koinViewModel()
        )
    }
}