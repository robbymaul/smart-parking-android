package com.dev.smartparking.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.smartparking.domain.model.ForgotPasswordModel
import com.dev.smartparking.domain.model.ForgotPasswordVerifyOtpModel
import com.dev.smartparking.domain.model.ResetPasswordModel
import com.dev.smartparking.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(private val authUseCase: AuthUseCase) : ViewModel() {
    // states
    var phoneNumber by mutableStateOf("")
        private set

    var otp1 by mutableStateOf("")
        private set

    var otp2 by mutableStateOf("")
        private set

    var otp3 by mutableStateOf("")
        private set

    var otp4 by mutableStateOf("")
        private set

    var otp5 by mutableStateOf("")
        private set

    var otp6 by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var confirmPassword by mutableStateOf("")
        private set

    var forgotPasswordModel by mutableStateOf<ForgotPasswordModel?>(null)
        private set

    var forgotPasswordVerifyOtpModel by mutableStateOf<ForgotPasswordVerifyOtpModel?>(null)
        private set

    var resetPasswordModel by mutableStateOf<ResetPasswordModel?>(null)

    // UI states
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isForgotPasswordFailed by mutableStateOf(false)
        private set

    var isForgotPasswordSuccessful by mutableStateOf(false)
        private set

    var isForgotPasswordVerifyOtpSuccessful by mutableStateOf(false)
        private set

    var isForgotPasswordVerifyOtpFailed by mutableStateOf(false)
        private set

    var isResetPasswordSuccessful by mutableStateOf(false)
        private set

    var isResetPasswordFailed by mutableStateOf(false)
        private set

    var phoneNumberError by mutableStateOf<String?>(null)
        private set

    // handler
    fun onPhoneNumberChange(value: String) {
        val cleaned = value.removePrefix("0")
        phoneNumber = cleaned
        phoneNumberError = null
    }

    fun otp1Change(value: String) {
        otp1 = value
    }

    fun otp2Change(value: String) {
        otp2 = value
    }

    fun otp3Change(value: String) {
        otp3 = value
    }

    fun otp4Change(value: String) {
        otp4 = value
    }

    fun otp5Change(value: String) {
        otp5 = value
    }

    fun otp6Change(value: String) {
        otp6 = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    fun onConfirmPasswordChange(value: String) {
        confirmPassword = value
    }

    fun isLoadingChange(value: Boolean) {
        isLoading = value
    }

    fun isForgotPasswordFailedChange(value: Boolean) {
        isForgotPasswordFailed = value
    }

    fun isForgotPasswordSuccessfulChange(value: Boolean) {
        isForgotPasswordSuccessful = value
    }

    fun isForgotPasswordVerifyOtpSuccessfulChange(value: Boolean) {
        isForgotPasswordVerifyOtpSuccessful = value
    }

    fun isForgotPasswordVerifyOtpFailedChange(value: Boolean) {
        isForgotPasswordVerifyOtpFailed = value
    }

    fun isResetPasswordSuccessfulChange(value: Boolean) {
        isResetPasswordSuccessful = value
    }

    fun isResetPasswordFailedChange(value: Boolean) {
        isResetPasswordFailed = value
    }

    // validation
    private fun validationForgotPassword(): Boolean {
        if (phoneNumber.isBlank()) {
            errorMessage = "nomor handphone tidak boleh kosong"
            isForgotPasswordFailed = true
            return false
        }

        return true
    }

    private fun validationVerifyOtp(): Boolean {
        if (forgotPasswordModel == null) {
            errorMessage = "something wrong!"
            isForgotPasswordVerifyOtpFailed = true
        }

        if (otp1.isBlank()) {
            errorMessage = "otp tidak valid"
            isForgotPasswordVerifyOtpFailed = true
            return false
        }

        if (otp2.isBlank()) {
            errorMessage = "otp tidak valid"
            isForgotPasswordVerifyOtpFailed = true
            return false
        }

        if (otp3.isBlank()) {
            errorMessage = "otp tidak valid"
            isForgotPasswordVerifyOtpFailed = true
            return false
        }

        if (otp4.isBlank()) {
            errorMessage = "otp tidak valid"
            isForgotPasswordVerifyOtpFailed = true
            return false
        }

        if (otp5.isBlank()) {
            errorMessage = "otp tidak valid"
            isForgotPasswordVerifyOtpFailed = true
            return false
        }

        if (otp6.isBlank()) {
            errorMessage = "otp tidak valid"
            isForgotPasswordVerifyOtpFailed = true
            return false
        }

        return true
    }

    private fun validationResetPassword(): Boolean {
        if (forgotPasswordVerifyOtpModel == null) {
            errorMessage = "something wrong!"
            isResetPasswordFailed = true
            return false
        }

        if (password.isBlank()) {
            errorMessage = "Password tidak boleh kosong"
            isResetPasswordFailed = true
            return false
        } else if (password.length < 8) {
            errorMessage = "Password minimal 8 karakter"
            isResetPasswordFailed = true
            return false
        }

        if (confirmPassword.isBlank()) {
            errorMessage = "Password tidak boleh kosong"
            isResetPasswordFailed = true
            return false
        } else if (confirmPassword.length < 8) {
            errorMessage = "Password minimal 8 karakter"
            isResetPasswordFailed = true
            return false
        }

        if (password != confirmPassword) {
            errorMessage = "Password dan konfirmasi password tidak sama"
            isResetPasswordFailed = true
            return false
        }

        return true
    }


    // Action UseCase Function
    fun forgotPassword(onSuccess: () -> Unit) {
        if (!validationForgotPassword()) {
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = authUseCase.forgotPassword(
                    phoneNumber = phoneNumber,
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let { forgotPasswordResponse ->
                    isForgotPasswordSuccessful = true
                    forgotPasswordModel = forgotPasswordResponse
                } ?: run {
                    isForgotPasswordFailed = true
                    errorMessage =
                        result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                }

                if (isForgotPasswordSuccessful) {
                    onSuccess()
                }
            } catch (e: Exception) {
                isForgotPasswordFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
            } finally {
                isLoading = false
            }
        }
    }

    fun forgotPasswordVerifyOtp(onSuccess: () -> Unit) {
        if (!validationVerifyOtp()) {
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = authUseCase.forgotPasswordVerifyOtp(
                    phoneNumber = forgotPasswordModel?.phoneNumber ?: "",
                    otp = otp1.plus(otp2.plus(otp3.plus(otp4.plus(otp5.plus(otp6)))))
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let { forgotPasswordVerifyOtpResponse ->
                    isForgotPasswordVerifyOtpSuccessful = true
                    forgotPasswordVerifyOtpModel = forgotPasswordVerifyOtpResponse
                } ?: run {
                    isForgotPasswordVerifyOtpFailed = true
                    errorMessage =
                        result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                }

                if (isForgotPasswordVerifyOtpSuccessful) {
                    onSuccess()
                }
            } catch (e: Exception) {
                isForgotPasswordVerifyOtpFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
            } finally {
                isLoading = false
            }
        }
    }

    fun resetPassword(onSuccess: () -> Unit) {
        if (!validationResetPassword()) {
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = authUseCase.resetPassword(
                    phoneNumber = forgotPasswordVerifyOtpModel?.phoneNumber ?: "",
                    token = forgotPasswordVerifyOtpModel?.token ?: "",
                    password = password,
                    confirmPassword = confirmPassword
                )

                isLoading = result.isLoading()

                result.getOrNull()?.let { resetPasswordResponse ->
                    isResetPasswordSuccessful = true
                    resetPasswordModel = resetPasswordResponse
                } ?: run {
                    isResetPasswordFailed = true
                    errorMessage =
                        result.exceptionOrNull()?.message ?: "Terjadi Kesalahan saat login"
                }

                if (isResetPasswordSuccessful) {
                    onSuccess()
                    resetStated()
                }
            } catch (e: Exception) {
                isResetPasswordFailed = true
                errorMessage = e.message ?: "Terjadi kesahalan pada sistem"
            } finally {
                isLoading = false
            }
        }
    }

    fun resetStated() {
        // states
        phoneNumber = ""
        otp1 = ""
        otp2 = ""
        otp3 = ""
        otp4 = ""
        otp5 = ""
        otp6 = ""
        password = ""
        confirmPassword = ""
        errorMessage = null
        phoneNumberError = null

        // UI states
        isForgotPasswordFailed = false
        isForgotPasswordSuccessful = false
        isForgotPasswordVerifyOtpSuccessful = false
        isForgotPasswordVerifyOtpFailed = false
        isResetPasswordSuccessful = false
        isResetPasswordFailed = false
        isLoading = false
        forgotPasswordModel = null
        forgotPasswordVerifyOtpModel = null
        resetPasswordModel = null
    }
}