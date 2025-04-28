package com.dev.smartparking.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.dev.smartparking.R
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.card.MallCard
import com.dev.smartparking.ui.component.MenuCategoriesComponent
import com.dev.smartparking.ui.section.BannerSection
import com.dev.smartparking.ui.section.ContentSection
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun HomepageScreen(modifier: Modifier = Modifier, navController: NavHostController?) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuCategoriesComponent()
        BannerSection()
        ContentSection(
            title = R.string.txt_title_recommendation
        ) {
            LazyRow {
                items( count = 7) {
                    MallCard(
                        imageUrl = "https://res.cloudinary.com/dxdtxld4f/image/upload/v1738768682/skripsi/image_mall1_ixzb6u.jpg",
                        rating = "4.5",
                        mallName = "Margonda City Mall",
                        isLike = true,
                        onClickButton = {
                            navController?.navigate(Screen.DetailMall.route)
                        }
                    )
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun HomepageScreenPreview() {
    SmartParkingTheme {
        HomepageScreen(navController = null)
    }
}