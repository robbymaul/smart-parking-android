package com.dev.smartparking.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.R
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.card.MallCard
import com.dev.smartparking.ui.component.MenuCategoriesComponent
import com.dev.smartparking.ui.section.BannerSection
import com.dev.smartparking.ui.section.ContentSection
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.HomepageViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.lazy.items


@Composable
fun HomepageScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homepageViewModel: HomepageViewModel,
    mainNavController: NavHostController
) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuCategoriesComponent()
        BannerSection()
        ContentSection(
            title = R.string.txt_title_recommendation,
            content = {
                LazyRow {
                    items(items = homepageViewModel.placesModel, key = {it.id}) { place ->
                        MallCard(
                            imageUrl = place.image,
                            rating = "4.5", // Sesuaikan dengan data `place` jika tersedia
                            mallName = place.name,
                            isLike = true, // Sesuaikan dengan data `place` jika tersedia
                            onClickButton = {
                                mainNavController.navigate("${Screen.DetailMall.route}/${place.id}")
                            }
                        )
                    }
                }
            },
        )
    }
}

@Preview (showBackground = true)
@Composable
private fun HomepageScreenPreview() {
    SmartParkingTheme {
        HomepageScreen(
            navController = rememberNavController(),
            homepageViewModel = koinViewModel(),
            mainNavController = rememberNavController()
        )
    }
}