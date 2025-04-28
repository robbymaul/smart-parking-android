package com.dev.smartparking.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dev.smartparking.route.Screen

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.Homepage to Icons.Default.Home,
        Screen.Ticket to Icons.Default.ConfirmationNumber,
        Screen.Notification to Icons.Default.Notifications,
        Screen.Profile to Icons.Default.Person
    )

    BottomNavigation (
        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { (screen, icon) ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (currentRoute == screen.route) Color.Black else Color.White
                    )
                },
                label = { Text(screen.route) },
                selected = currentRoute == screen.route,
                onClick = { navController.navigate(screen.route) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.White
            )
        }
    }
}