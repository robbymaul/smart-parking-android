package com.dev.smartparking.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.domain.model.UserModel
import com.dev.smartparking.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class IndexViewModel(
    private val userUseCase: UserUseCase,
    private val authPreferences: AuthPreferences
) : ViewModel() {
    // States
    var userModel by mutableStateOf<UserModel?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    // UI states
    var isLoading by mutableStateOf(false)
        private set

    var isGetUserSuccessful by mutableStateOf(false)
        private set

    var isGetUserFailed by mutableStateOf(false)
        private set

    // Handler
    fun isErrorMessageChange(value: String) {
        errorMessage = value
    }

    fun isLoadingChange(value: Boolean) {
        isLoading = value
    }

    fun isGetUserSuccessful(value: Boolean) {
        isGetUserSuccessful = value
    }

    fun isGetUserFailed(value: Boolean) {
        isGetUserFailed = value
    }

    // validation


    // init statement
    init {
        getUser() {}
    }

    // fetch
    private fun getUser(onFailed: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = userUseCase.getUser()

                isLoading = result.isLoading()

                result.getOrNull()?.let {
                    isGetUserSuccessful = true
                } ?: run {
                    isGetUserFailed = true
                    errorMessage = result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                }

                if (isGetUserFailed) {
                    Log.d("get user", "is get user $isGetUserFailed")
                    authPreferences.clearUserData()
                }
            } catch (e: Exception) {
                isGetUserFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
                if (isGetUserFailed) {
                    Log.d("catch get user exception", "catch get user exception $isGetUserFailed")
                    authPreferences.clearUserData()
                }
            } finally {
                isLoading = false
            }
        }
    }
}