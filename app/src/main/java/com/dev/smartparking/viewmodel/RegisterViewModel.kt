package com.dev.smartparking.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.smartparking.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    // Form states
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var phoneNumber by mutableStateOf("")
        private set

    var firstName by mutableStateOf("")
        private set

    var lastName by mutableStateOf("")
        private set

    // Validation states
//    var usernameError by mutableStateOf<String?>(null)
//        private set

    var emailError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var phoneNumberError by mutableStateOf<String?>(null)
        private set

    var firstNameError by mutableStateOf<String?>(null)
        private set

    var lastNameError by mutableStateOf<String?>(null)
        private set

    // UI states
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isRegistrationSuccessful by mutableStateOf(false)
        private set

    var isRegistrationFailed by mutableStateOf(false)
        private set

    // Input handlers
//    fun onUsernameChange(value: String) {
//        username = value
//        usernameError = null
//    }

    fun onEmailChange(value: String) {
        email = value
        emailError = null
    }

    fun onPasswordChange(value: String) {
        password = value
        passwordError = null
    }

    fun onPhoneNumberChange(value: String) {
        val cleaned = value.removePrefix("0")
        phoneNumber = cleaned
        phoneNumberError = null
    }

    fun onFirstNameChange(value: String) {
        firstName = value
        firstNameError = null
    }

    fun onLastNameChange(value: String) {
        lastName = value
        lastNameError = null
    }

    fun onIsRegisterFailedChange(value: Boolean) {
        isRegistrationFailed = value
    }

    fun onIsRegisterSuccessfulChange(value: Boolean) {
        isRegistrationSuccessful = value
    }

    // Validation
    private fun validateInputs(): Boolean {
//        if (username.isBlank()) {
//            usernameError = "Username tidak boleh kosong"
//            isValid = false
//        }

        if (email.isBlank()) {
            errorMessage = "Email tidak boleh kosong"
            isRegistrationFailed = true
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage = "Format email tidak valid"
            isRegistrationFailed = true
            return false
        }

        if (password.isBlank()) {
            isRegistrationFailed = true
            errorMessage = "Password tidak boleh kosong"
            return false
        } else if (password.length < 8) {
                isRegistrationFailed = true
                errorMessage = "Password minimal 8 karakter"
                return false
        }

        if (phoneNumber.isBlank()) {
            isRegistrationFailed = true
            errorMessage = "Nomor telepon tidak boleh kosong"
            return false
        } else if (!phoneNumber.matches(Regex("^\\+?[0-9]{10,13}$"))) {
            isRegistrationFailed = true
            errorMessage = "Format nomor telepon tidak valid"
            return false
        }

        if (firstName.isBlank()) {
            isRegistrationFailed = true
            errorMessage = "Nama depan tidak boleh kosong"
            return false
        }

        if (lastName.isBlank()) {
            isRegistrationFailed = true
            errorMessage = "Nama belakang tidak boleh kosong"
            return false
        }

        return true
    }

    // Register function
    fun register(onSuccess: ()-> Unit) {
        if (!validateInputs()) {
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = authUseCase.register(
//                    username = username,
                    email = email,
                    password = password,
                    phoneNumber = phoneNumber,
                    firstName = firstName,
                    lastName = lastName
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let {
                    onIsRegisterSuccessfulChange(true)
                } ?: run {
                    onIsRegisterFailedChange(true)
                    errorMessage = result.exceptionOrNull()?.message ?: "Terjadi kesalahan saat registrasi"
                }

                if (isRegistrationSuccessful) {
                    resetStates()
                    onSuccess()
                }
            } catch (e: Exception) {
                onIsRegisterFailedChange(true)
                errorMessage = e.message ?: "Terjadi kesalahan pada sistem"
            } finally {
                isLoading = false
            }
        }
    }

    // Reset states
    fun resetStates() {
//        username = ""
        email = ""
        password = ""
        phoneNumber = ""
        firstName = ""
        lastName = ""

//        usernameError = null
        emailError = null
        passwordError = null
        phoneNumberError = null
        firstNameError = null
        lastNameError = null

        isLoading = false
        errorMessage = null
        isRegistrationSuccessful = false
        isRegistrationFailed = false
    }
}