package com.dev.smartparking.route

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Intro : Screen("intro")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object ForgotPassword : Screen("forgot_password")
    data object OTP : Screen("otp")
    data object ForgotPasswordOTP : Screen("forgot_password_otp")
    data object ResetPassword: Screen("reset_password")
    data object Activation : Screen("activation")
    data object Main : Screen("main")

    // 🔹 Nested Navigation untuk Tab Menu
    data object Homepage : Screen("homepage")
    data object Ticket : Screen("ticket")
    data object Notification : Screen("notification")
    data object Profile : Screen("profile")
    data object ProfileMyVehicle: Screen("profile/my_vehicle")
    data object DetailProfile: Screen("profile/detail")
    data object ProfilePassword: Screen("profile/password")

    data object DetailMall : Screen("mall/{id}")
    data object Parking : Screen("mall/{id}/parking")
    data object Payment : Screen("payment")
    data object CheckStatusPayment : Screen("payment/status/{id}")
    data object DetailTicket : Screen("ticket/{id}")
}