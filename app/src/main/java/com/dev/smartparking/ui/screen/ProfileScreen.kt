package com.dev.smartparking.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.ui.card.MenuProfileCard
import com.dev.smartparking.ui.card.ProfileCard
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mainNavController: NavHostController,
    profileViewModel: ProfileViewModel
) {

    Column(modifier = modifier) {
        ProfileCard(profileViewModel = profileViewModel, navController = navController)
        MenuProfileCard(navController = navController, profileViewModel = profileViewModel, mainNavController = mainNavController)
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    SmartParkingTheme {
        ProfileScreen(
            navController = rememberNavController(),
            mainNavController = rememberNavController(),
            profileViewModel = koinViewModel()
        )
    }
}