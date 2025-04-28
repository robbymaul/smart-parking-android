package com.dev.smartparking.viewmodel

import android.util.Log
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.smartparking.data.OTPCredentialData
import com.dev.smartparking.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OTPViewModel(private val authUseCase: AuthUseCase): ViewModel() {
    var phoneNumber by mutableStateOf("")
        private set

    var otp1 by  mutableStateOf("")
        private set

    var otp2 by  mutableStateOf("")
      private set

    var otp3 by  mutableStateOf("")
      private set

    var otp4 by  mutableStateOf("")
      private set

    var otp5 by  mutableStateOf("")
      private set

    var otp6 by  mutableStateOf("")
      private set

    // UI States
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isSendOTPSuccessful by mutableStateOf(false)
        private set

    var isResendOTPSuccessful by mutableStateOf(false)
        private set

    var isSendOTPFailed by mutableStateOf(false)
        private set

    var isResendOTPFailed by mutableStateOf(false)
        private set

    var isVerifyOTPSuccessful by mutableStateOf(false)
        private set

    var isVerifyOTPFailed by mutableStateOf(false)
        private set

    var resendOTPMessage by mutableStateOf("")
        private set

    // Input Handler
    fun phoneNumberChange(value: String) {
        phoneNumber = value
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

    fun isLoadingChange(value: Boolean) {
        isLoading = value
    }

    fun errorMessageChange(value: String) {
        errorMessage = value
    }

    fun isSendOTPSuccessfulChange(value: Boolean) {
        isSendOTPSuccessful = value
    }

    fun isResendOTPSuccessfulChange(value: Boolean) {
        isResendOTPSuccessful = value
    }

    fun isSendOTPFailedChange(value: Boolean) {
        isSendOTPFailed = value
    }

    fun isVerifyOTPSuccessfulChange(value: Boolean) {
        isVerifyOTPSuccessful = value
    }

    fun isVerifyOTPFailedChange(value: Boolean) {
        isVerifyOTPFailed = value
    }

    // validation
    private fun validationSendOtp(): Boolean {
        if (phoneNumber.isBlank()) {
            errorMessage = "nomor hp tidak ditemukan"
            isSendOTPFailed = true
            return false
        }

        Log.d("validationSendOTP", "trigggred")
        return true
    }

    private fun validationVerifyOtp(): Boolean {
        if (otp1.isBlank()) {
            errorMessage = "otp tidak valid"
            isVerifyOTPFailed = true
            return false
        }

        if (otp2.isBlank()) {
            errorMessage = "otp tidak valid"
            isVerifyOTPFailed = true
            return false
        }

        if (otp3.isBlank()) {
            errorMessage = "otp tidak valid"
            isVerifyOTPFailed = true
            return false
        }

        if (otp4.isBlank()) {
            errorMessage = "otp tidak valid"
            isVerifyOTPFailed = true
            return false
        }

        if (otp5.isBlank()) {
            errorMessage = "otp tidak valid"
            isVerifyOTPFailed = true
            return false
        }

        if (otp6.isBlank()) {
            errorMessage = "otp tidak valid"
            isVerifyOTPFailed = true
            return false
        }

        return true
    }

    fun sendOtp() {
        Log.d("sendOTP", "trigggred")
        if (!validationSendOtp()){
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val result = authUseCase.sendOtp(phoneNumber = phoneNumber)

                isLoading = result.isLoading()

                result.getOrNull()?.let {
                    isSendOTPSuccessful = true
                } ?: run {
                    isSendOTPFailed = true
                    errorMessage = result.exceptionOrNull()?.message ?: "Terjadi Kesalahan Pada Sistem"
                }
            } catch (e: Exception) {
                isSendOTPFailed = true
                errorMessage = e.message ?: "Terjadi Kesalahan Pada Sistem"
            } finally {
                isLoading = false
            }
        }
    }

    fun resendOtp() {
        Log.d("resendOTP", "trigggred")

        viewModelScope.launch {
            try {
                errorMessage = null

                val result = authUseCase.resendOtp()

                result.getOrNull()?.let { data ->
                    isResendOTPSuccessful = true
                    resendOTPMessage = data.message
                } ?: run {
                    isResendOTPFailed = true
                    errorMessage = result.exceptionOrNull()?.message ?: "Terjadi Kesalahan Pada Sistem"
                }
            } catch (e: Exception) {
                isResendOTPFailed = true
                errorMessage = e.message ?: "Terjadi Kesalahan Pada Sistem"
            } finally {
                isLoading = false
            }
        }
    }

    fun verifyOtp(onSuccess: () -> Unit) {
        Log.d("resendOTP", "trigggred")
        if (!validationVerifyOtp()) {
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val otp = otp1.plus(otp2.plus(otp3.plus(otp4.plus(otp5.plus(otp6)))))

                val result = authUseCase.verifyOtp(otp)

                isLoading = result.isLoading()

                result.getOrNull()?.let { data ->
                    isVerifyOTPSuccessful = true
                } ?: run {
                    isVerifyOTPFailed = true
                    errorMessage = result.exceptionOrNull()?.message ?: "Terjadi Kesalahan Pada Sistem"
                }

                if (isVerifyOTPSuccessful) {
                    onSuccess()
                    resetStates()
                }
            } catch (e: Exception) {
                isVerifyOTPFailed = true
                errorMessage = e.message ?: "Terjadi Kesalahan Pada Sistem"
            } finally {
                isLoading = false
            }
        }
    }

    private fun resetStates() {
        phoneNumber = ""
        otp1 = ""
        otp2 = ""
        otp3 = ""
        otp4 = ""
        otp5 = ""
        otp6 = ""

        isLoading = false
        errorMessage = null

        isResendOTPFailed = false
        isResendOTPSuccessful = false
        isSendOTPFailed = false
        isSendOTPSuccessful = false
        isVerifyOTPFailed = false
        isVerifyOTPSuccessful = false
    }
}

//
//class OTPViewModel: ViewModel() {
//    var otp1 by  mutableStateOf("")
//        private set
//
//    var otp2 by  mutableStateOf("")
//        private set
//
//    var otp3 by  mutableStateOf("")
//        private set
//
//    var otp4 by  mutableStateOf("")
//        private set
//
//    var otp4 by  mutableStateOf("")
//        private set
//
//
//    var isLoading by mutableStateOf(false)
//        private set
//
//    var errorMessage by mutableStateOf<String?>(null)
//        private set
//
//    var otpCredential by mutableStateOf(OTPCredentialData())
//        private set
//
//    fun onChangeOtp1(value: String) {
//        this.otp1 = value
//    }
//
//    fun onChangeOtp2(value: String) {
//        this.otp2 = value
//    }
//
//    fun onChangeOtp3(value: String) {
//        this.otp3 = value
//    }
//
//    fun onChangeOtp4(value: String) {
//        this.otp4 = value
//    }
//
//    fun formatOtp() {
//        otpCredential.otp =  "${this.otp1}${this.otp2}${this.otp3}${this.otp4}"
//    }
//
//    fun verify(onSuccess: ()-> Unit) {
//        viewModelScope.launch {
//            isLoading = true
//            delay(2000)
//
//            if (otpCredential.otp.isNotBlank()) {
//                if (otpCredential.otp == "1234") {
//                    onSuccess()
//                } else {
//                    errorMessage = "invalid otp"
//                }
//            } else {
//                errorMessage = "Please Enter Phone and Password"
//            }
//        }
//    }
//}