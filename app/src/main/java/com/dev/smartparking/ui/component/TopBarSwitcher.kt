package com.dev.smartparking.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dev.smartparking.route.Screen

@Composable
fun TopBarSwitcher(route: String?, bottomNavController: NavController) {
    when (route) {
        Screen.Homepage.route -> TopBarMenuHomepageComponent(navController = bottomNavController)
        Screen.Ticket.route -> TopBarComponent(
            title = "Ticket",
            onClickIcon = {}
        )
        Screen.Notification.route -> TopBarComponent(
            title = "Notification",
            onClickIcon = {}
        )
        Screen.Profile.route -> TopBarComponent(
            title = "Profile",
            onClickIcon = {}
        )
        Screen.ProfileMyVehicle.route -> TopBarComponent(
            title = "Profile",
            onClickIcon = {},
            navigationIcon = {
                IconButton(onClick = {
                    bottomNavController.popBackStack()
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                }
            }
        )
        Screen.DetailProfile.route -> TopBarComponent(
            title = "Profile",
            onClickIcon = {},
            navigationIcon = {
                IconButton(onClick = {
                    bottomNavController.popBackStack()
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                }
            }

        )
        Screen.ProfilePassword.route -> TopBarComponent(
            title = "Profile",
            onClickIcon = {},
            navigationIcon = {
                IconButton(onClick = {
                    bottomNavController.popBackStack()
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                }
            }
        )
        else -> {}
    }
}