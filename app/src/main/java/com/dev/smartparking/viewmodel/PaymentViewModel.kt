package com.dev.smartparking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.dev.smartparking.route.Screen

class PaymentViewModel() : ViewModel() {

    fun handleClickNavigationIcon(navController: NavHostController?) {
        navController?.popBackStack()
    }

    fun handleClickPayment(navController: NavHostController?) {
        navController?.navigate(Screen.DetailTicket.route) {
            popUpTo(Screen.Payment.route) {
                inclusive = true
            }
        }
    }
}