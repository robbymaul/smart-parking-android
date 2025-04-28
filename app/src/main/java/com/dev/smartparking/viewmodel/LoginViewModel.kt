package com.dev.smartparking.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.smartparking.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch


class LoginViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    // Form States
    var phoneNumber by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var phoneNumberError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    // UI States
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isLoginSuccessful by mutableStateOf(false)
        private set

    var isLoginFailed by mutableStateOf(false)
        private set

    // Input Handler
    fun onPhoneNumberChange(value: String) {
        val cleaned = value.removePrefix("0")
        phoneNumber = cleaned
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    fun onIsLoginSuccessfulChange(value: Boolean) {
        isLoginSuccessful = value
    }

    fun onIsLoginFailedChange(value: Boolean) {
        isLoginFailed = value
    }

    fun isLoadingChange(value: Boolean) {
        isLoading = value
    }


    // validation
    private fun validationInput(): Boolean {
        if (phoneNumber.isBlank()) {
            errorMessage = "nomor hp tidak boleh kosong"
            isLoginFailed = true
            return false
        } else if (!phoneNumber.matches(Regex("^\\+?[0-9]{10,13}$"))) {
            errorMessage = "Format nomor telepon tidak valid"
            isLoginFailed = true
            return false
        }

        if (password.isBlank()) {
            errorMessage = "Password tidak boleh kosong"
            isLoginFailed = true
            return false
        } else if (password.length < 8) {
            errorMessage = "Password minimal 8 karakter"
            isLoginFailed = true
            return false
        }

        return true
    }

    fun login(onSuccess: () -> Unit) {
        if (!validationInput()) {
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = authUseCase.login(
                    phoneNumber = phoneNumber,
                    password = password
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let {
                    isLoginSuccessful = true
                } ?: run {
                    isLoginFailed = true
                    errorMessage = result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                }

                if (isLoginSuccessful) {
                    resetStates()
                        onSuccess()
                }
            } catch (e: Exception) {
                isLoginFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
            } finally {
                onPasswordChange("")
                onPhoneNumberChange("")
                isLoading = false
            }
        }
    }

    private fun resetStates() {
        phoneNumber = ""
        password = ""

        phoneNumberError = null
        passwordError = null

        isLoading = false
        errorMessage = null
        isLoginSuccessful = false
        isLoginFailed = false
    }
}

//
//class LoginViewModel: ViewModel() {
//    var phoneNumber by mutableStateOf("")
//        private set
//
//    var password by mutableStateOf("")
//        private set
//
//    var visiblePassword by mutableStateOf(false)
//        private set
//
//    var isLoading by mutableStateOf(false)
//        private set
//
//    var errorMessage by mutableStateOf<String?>(null)
//        private set
//
//    var loginCredential by mutableStateOf(LoginCredentialData())
//        private set
//
//    fun onChangedPhoneNumber(phoneNumber: String) {
//        this.phoneNumber = phoneNumber
//        loginCredential = loginCredential.copy(phoneNumber = this.phoneNumber)
//    }
//
//    fun onChangedPassword(password: String) {
//        this.password = password
//        loginCredential = loginCredential.copy(password = this.password)
//    }
//
//    fun handleInvisiblePassword(visible: Boolean) {
//        this.visiblePassword = visible
//    }
//
//    fun handleCreateAccount(navController: NavHostController) {
//        navController.navigate(Screen.Register.route)
//    }
//
//    fun login(onSuccess: ()-> Unit) {
//        viewModelScope.launch {
//            isLoading = true
//            errorMessage = null
//            delay(2000)
//            if (loginCredential.isNotBlank() && loginCredential.isNotEmpty()) {
//                if (loginCredential.phoneNumber == "082122846711" && loginCredential.password == "rahasia") {
//                    onSuccess()
//                } else {
//                    errorMessage = "Invalid phone number or password."
//                }
//            } else {
//                errorMessage = "Please Enter Phone and Password"
//            }
//
//            isLoading = false
//        }
//    }
//}