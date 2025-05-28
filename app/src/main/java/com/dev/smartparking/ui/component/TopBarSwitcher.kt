package com.dev.smartparking.ui.component

import androidx.compose.runtime.Composable
import com.dev.smartparking.route.Screen

@Composable
fun TopBarSwitcher(route: String?) {
    when (route) {
        Screen.Homepage.route -> TopBarMenuHomepageComponent()
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
            onClickIcon = {}
        )
        Screen.DetailProfile.route -> TopBarComponent(
            title = "Profile",
            onClickIcon = {}
        )
        Screen.ProfilePassword.route -> TopBarComponent(
            title = "Profile",
            onClickIcon = {}
        )
        else -> {}
    }
}