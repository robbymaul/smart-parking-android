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
import com.dev.smartparking.ui.screen.DetailProfileScreen
import com.dev.smartparking.ui.screen.HomepageScreen
import com.dev.smartparking.ui.screen.NotificationScreen
import com.dev.smartparking.ui.screen.ProfileMyVehicleScreen
import com.dev.smartparking.ui.screen.ProfilePasswordScreen
import com.dev.smartparking.ui.screen.ProfileScreen
import com.dev.smartparking.ui.screen.TicketScreen
import com.dev.smartparking.viewmodel.HomepageViewModel
import com.dev.smartparking.viewmodel.IndexViewModel
import com.dev.smartparking.viewmodel.ProfileViewModel
import com.dev.smartparking.viewmodel.TicketViewModel
import kotlinx.coroutines.flow.first
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun IndexActivity(modifier: Modifier = Modifier, navController: NavHostController) {
    val bottomNavController = rememberNavController()
    val indexViewModel: IndexViewModel = koinViewModel()
    val homepageViewModel: HomepageViewModel = koinViewModel()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRout = navBackStackEntry?.destination?.route
    val authPreferences: AuthPreferences = koinInject()
    val profileViewModel: ProfileViewModel = koinViewModel()
    val ticketViewModel: TicketViewModel = koinViewModel()


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
        topBar = { TopBarSwitcher(route = currentRout, bottomNavController = bottomNavController) },
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
                    navController = bottomNavController,
                    mainNavController = navController,
                    homepageViewModel = homepageViewModel
                )
            }
            composable(Screen.Ticket.route) {
                TicketScreen(
                    bottomNavController = bottomNavController,
                    ticketViewModel = ticketViewModel,
                    mainNavController = navController
                )
            }
            composable(Screen.Notification.route) {
                NotificationScreen(
                    navController = bottomNavController
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navController = bottomNavController,
                    mainNavController = navController,
                    profileViewModel = profileViewModel
                )
            }
            composable(route = Screen.ProfileMyVehicle.route) {
                ProfileMyVehicleScreen(
                    navController = bottomNavController,
                    profileViewModel = profileViewModel
                )
            }
            composable(route = Screen.DetailProfile.route) {
                DetailProfileScreen(
                    navController = bottomNavController,
                    profileViewModel = profileViewModel
                )
            }
            composable(route = Screen.ProfilePassword.route) {
                ProfilePasswordScreen(
                    navController = bottomNavController,
                    profileViewModel = profileViewModel
                )
            }
        }
    }

    LoadingDialog(isLoading = indexViewModel.isLoading)
}

