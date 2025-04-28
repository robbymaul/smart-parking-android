package com.dev.smartparking.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authUseCase: AuthUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel() {

    // UI states
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Input Handler
    fun isLoadingChange(value: Boolean) {
        isLoading = value
    }

    fun onErrorMessageChange(value: String) {
        errorMessage = value
    }

    fun logout() {
        viewModelScope.launch {
            val refreshToken = authPreferences.refreshToken.first()
            try {
                val result = authUseCase.logout(refreshToken = refreshToken)

                isLoading = result.isLoading()
            } catch (e: Exception) {
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
            } finally {
                isLoading = false
                authPreferences.clearUserData()
            }
        }
    }
}