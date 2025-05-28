package com.dev.smartparking.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dev.smartparking.activity.ActivationActivity
import com.dev.smartparking.activity.AuthForgotPasswordActivity
import com.dev.smartparking.activity.HomepageActivity
import com.dev.smartparking.activity.IndexActivity
import com.dev.smartparking.activity.IntroActivity
import com.dev.smartparking.activity.LoginActivity
import com.dev.smartparking.activity.OtpActivity
import com.dev.smartparking.activity.PaymentActivity
import com.dev.smartparking.activity.RegisterActivity
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.screen.DetailMallScreen
import com.dev.smartparking.ui.screen.DetailTicketScreen
import com.dev.smartparking.ui.screen.ParkingSlotScreen
import com.dev.smartparking.ui.screen.Splash
import com.dev.smartparking.ui.screen.SplashScreen
import kotlinx.coroutines.flow.first
import org.koin.compose.koinInject


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
//    startDestination: String = determineStartDestination()
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.Intro.route) {
            IntroActivity(navController = navController)
        }

//         Auth screens
        composable(Screen.Login.route) {
            LoginActivity(navController = navController)
        }

        composable(Screen.Register.route) {
//            RegisterScreen(navController)
            RegisterActivity(navController = navController)
        }

//         Main screens
        composable(Screen.Homepage.route) {
//            HomepageActivity(navController = navController)
            HomepageActivity()
        }


        composable(Screen.ForgotPassword.route) {
            AuthForgotPasswordActivity(
                navController = navController
            )
        }

        composable(Screen.OTP.route) {
            OtpActivity(
                navController = navController
            )
        }
        composable(Screen.Activation.route) {
            ActivationActivity(
                navController = navController
            )
        }

        composable(Screen.Main.route) {
            IndexActivity(
                navController = navController
            )
        }

        composable(
            route = "${Screen.DetailMall.route}/{mallId}",
            arguments = listOf(
                navArgument("mallId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val mallId = backStackEntry.arguments?.getInt("mallId") ?: return@composable
            DetailMallScreen(navController = navController, mallId = mallId)
        }

        composable(
            "${Screen.Parking.route}/{name}/{mallId}/{price}/{nextPrice}",
            arguments = listOf(
                navArgument(name = "name") {type = NavType.StringType},
                navArgument("mallId") { type = NavType.IntType },
                navArgument("price") { type = NavType.IntType },
                navArgument("nextPrice") { type = NavType.IntType },
            )
        ) { backStackEntry ->
            val mallId = backStackEntry.arguments?.getInt("mallId") ?: return@composable
            val price = backStackEntry.arguments?.getInt("price") ?: return@composable
            val nextPrice = backStackEntry.arguments?.getInt("nextPrice") ?: return@composable
            val name = backStackEntry.arguments?.getString("name") ?: return@composable
            ParkingSlotScreen(
                navController = navController, name = name, mallId = mallId, price = price, nextPrice = nextPrice
            )
        }

        composable(
            route = "${Screen.Payment.route}/{bookingId}",
            arguments = listOf(
                navArgument("bookingId") {type = NavType.IntType}
            )
            ) { backStackEntry ->
            val bookingId = backStackEntry.arguments?.getInt("bookingId") ?: return@composable
            PaymentActivity(
                navController = navController, bookingId = bookingId
            )
        }

        composable(Screen.DetailTicket.route) {
            DetailTicketScreen(
                navController = navController,
                bookingId = 26
            )
        }

//        composable(Screen.Profile.route) {
//            ProfileScreen(navController)
//        }

        // Detail screen with argument
//        composable(
//            route = Screen.ParkingDetail.route,
//            arguments = listOf(
//                navArgument("parkingId") {
//                    type = NavType.StringType
//                }
//            )
//        ) { backStackEntry ->
//            val parkingId = backStackEntry.arguments?.getString("parkingId") ?: ""
//            // ParkingDetailScreen(navController, parkingId)
//        }
    }
}

//@Composable
//private fun determineStartDestination(): String {
//    val authPreferences = koinInject<AuthPreferences>()
//
//    // Menggunakan remember untuk caching hasil, hindari recalculation pada recomposition
//    val startDestination = remember {
//        // Pemeriksaan token untuk menentukan state login
//        runBlocking {
//            val token = authPreferences.authToken.first()
//            if (token.isNotEmpty()) Screen.Homepage.route else Screen.Login.route
//        }
//    }
//
//    return startDestination
//}

//
//@Composable
//private fun determineStartDestination(): String {
//    val authPreferences = koinInject<AuthPreferences>()
//
//    val tokenState = produceState(initialValue = "") {
//        value = authPreferences.authToken.first()
//    }
//
//    return if (tokenState.value.isNotEmpty()) {
//        Screen.Homepage.route
//    } else {
//        Screen.Login.route
//    }
//}

@Composable
fun SmartParkingAppEntry() {
    val authPreferences = koinInject<AuthPreferences>()

    val tokenState = produceState<String?>(initialValue = null) {
        value = authPreferences.refreshToken.first()
    }

    // Tampilkan splash/loading saat token belum ready
    if (tokenState.value == null) {
        Splash() // atau loading indicator lain
    } else {
        val startDestination = if (tokenState.value!!.isNotEmpty()) {
            Screen.Main.route
        } else {
            Screen.Intro.route
        }

        NavGraph(startDestination = startDestination)
    }
}
