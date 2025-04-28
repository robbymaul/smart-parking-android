package com.dev.smartparking.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.dev.smartparking.data.IntroScreenData
import com.dev.smartparking.provider.IntroScreenProvider
import com.dev.smartparking.route.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class IntroViewModel : ViewModel() {
    // Menyimpan currentPage sebagai state logika
    private val _currentPage = mutableStateOf(0)
    val currentPage: Int get() = _currentPage.value

    private val _listScreen = MutableStateFlow(IntroScreenProvider.getIntroScreens())
    val listenScreen: StateFlow<List<IntroScreenData>> = _listScreen

    fun navigateToLogin(navController: NavHostController) {
        navController.navigate(Screen.Login.route) {
            popUpTo(Screen.Intro.route) { inclusive = true }
        }
    }
}

