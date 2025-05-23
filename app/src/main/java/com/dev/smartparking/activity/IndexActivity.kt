package com.dev.smartparking.activity

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.component.TopBarSwitcher
import com.dev.smartparking.ui.element.LoadingDialog
import com.dev.smartparking.ui.navigation.BottomNavigationBar
import com.dev.smartparking.ui.screen.HomepageScreen
import com.dev.smartparking.ui.screen.NotificationScreen
import com.dev.smartparking.ui.screen.ProfileScreen
import com.dev.smartparking.ui.screen.TicketScreen
import com.dev.smartparking.viewmodel.IndexViewModel
import kotlinx.coroutines.flow.first
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun IndexActivity(modifier: Modifier = Modifier, navController: NavHostController) {
    val bottomNavController = rememberNavController()
    val indexViewModel: IndexViewModel = koinViewModel()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRout = navBackStackEntry?.destination?.route
    val authPreferences: AuthPreferences = koinInject()

    LaunchedEffect(Unit) {
        authPreferences.user.first()?.let {
            val isVehicleActivated = it.isVehicleActivated

            if (!isVehicleActivated) {
                navController.navigate(Screen.Activation.route) {
                    popUpTo(Screen.Main.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = { TopBarSwitcher(currentRout) },
        bottomBar = {
            BottomNavigationBar(bottomNavController)
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = Screen.Homepage.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Homepage.route) {
                HomepageScreen(
                    navController = navController
                )
            }
            composable(Screen.Ticket.route) {
                TicketScreen(
                    navController = navController
                )
            }
            composable(Screen.Notification.route) {
                NotificationScreen(
                    navController = navController
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navController = navController
                )
            }
        }
    }

    LoadingDialog(isLoading = indexViewModel.isLoading)
}

