package com.dev.smartparking.domain.usecase

import com.dev.smartparking.data.repository.AuthRepository
import com.dev.smartparking.data.utils.Result
import com.dev.smartparking.domain.model.ForgotPasswordModel
import com.dev.smartparking.domain.model.ForgotPasswordVerifyOtpModel
import com.dev.smartparking.domain.model.LoginUser
import com.dev.smartparking.domain.model.LogoutModel
import com.dev.smartparking.domain.model.RegisterUser
import com.dev.smartparking.domain.model.ResetPasswordModel
import com.dev.smartparking.domain.model.SendPhoneOtpModel
import com.dev.smartparking.domain.model.VerifyOTPModel

class AuthUseCase(private val authRepository: AuthRepository) {
    suspend fun register(
        email: String,
        password: String,
        phoneNumber: String,
        firstName: String,
        lastName: String
    ): Result<RegisterUser> {
        val result = authRepository.register(
            email = email,
            password = password,
            phoneNumber = phoneNumber,
            firstName = firstName,
            lastName = lastName
        )

        return when (result) {
            is Result.Success -> {
                val userData = result.data.data
                Result.Success(
                    RegisterUser(
                        username = userData.username,
                        email = userData.email,
                        phoneNumber = userData.phoneNumber,
                        firstName = userData.firstName,
                        lastName = userData.lastName,
//                            isLoggedIn = userData.token != null
                    )
                )
            }
            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun login(
        phoneNumber: String,
        password: String
    ): Result<LoginUser> {
        val result = authRepository.login(
            phoneNumber = phoneNumber,
            password = password
        )

        return when (result) {
            is Result.Success -> {
                val loginData = result.data.data
                Result.Success(
                    LoginUser(
                        accessToken = loginData.accessToken
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun sendOtp(
        phoneNumber: String
    ): Result<SendPhoneOtpModel> {
        return when (val result = authRepository.sendPhoneOTP(phoneNumber)) {
            is Result.Success -> {
                val sendPhoneOtpData = result.data.data
                Result.Success(
                    SendPhoneOtpModel(
                        success = sendPhoneOtpData.success,
                        phoneNumber = sendPhoneOtpData.phoneNumber,
                        message = sendPhoneOtpData.message,
                        expiryMinutes = sendPhoneOtpData.expiryMinutes
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun resendOtp(): Result<SendPhoneOtpModel> {
        return when (val result = authRepository.resendPhoneOTP()) {
            is Result.Success -> {
                val sendPhoneOtpData = result.data.data
                Result.Success(
                    SendPhoneOtpModel(
                        success = sendPhoneOtpData.success,
                        phoneNumber = sendPhoneOtpData.phoneNumber,
                        message = sendPhoneOtpData.message,
                        expiryMinutes = sendPhoneOtpData.expiryMinutes
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun verifyOtp(otp: String): Result<VerifyOTPModel> {
        return when (val result = authRepository.verifyOTP(otp)) {
            is Result.Success -> {
                val verifyOtpData = result.data.data
                Result.Success(
                    VerifyOTPModel(
                        success = verifyOtpData.success,
                        phoneNumber = verifyOtpData.phoneNumber,
                        message = verifyOtpData.message,
                        token = verifyOtpData.token
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun logout(refreshToken: String): Result<LogoutModel> {
        return when (val result = authRepository.logout(refreshToken)) {
            is Result.Success -> {
                val logoutData = result.data.data
                Result.Success(
                    LogoutModel(
                        success = logoutData.success,
                        message = logoutData.message,
                        loggedOut = logoutData.loggedOut
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun forgotPassword(phoneNumber: String): Result<ForgotPasswordModel> {
        return when (val result = authRepository.forgotPassword(phoneNumber = "+62".plus(phoneNumber))) {
            is Result.Success -> {
                val forgotPasswordData = result.data.data
                Result.Success(
                    ForgotPasswordModel(
                        success = forgotPasswordData.success,
                        message = forgotPasswordData.message,
                        phoneNumber = forgotPasswordData.phoneNumber,
                        expiryMinutes = forgotPasswordData.expiryMinutes
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun forgotPasswordVerifyOtp(phoneNumber: String, otp: String): Result<ForgotPasswordVerifyOtpModel> {
        return when (val result = authRepository.forgotPasswordVerifyOtp(phoneNumber = phoneNumber, otp = otp)) {
            is Result.Success -> {
                val forgotPasswordVerifyOtpData = result.data.data
                Result.Success(
                    ForgotPasswordVerifyOtpModel(
                        success = forgotPasswordVerifyOtpData.success,
                        message = forgotPasswordVerifyOtpData.message,
                        phoneNumber = forgotPasswordVerifyOtpData.phoneNumber,
                        token = forgotPasswordVerifyOtpData.token,
                        validUntil = forgotPasswordVerifyOtpData.validUntil
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun resetPassword(phoneNumber: String, token: String, password: String, confirmPassword: String): Result<ResetPasswordModel> {
        return when (val result = authRepository.resetPassword(phoneNumber = phoneNumber, token = token, password = password, confirmPassword = confirmPassword)) {
            is Result.Success -> {
                val resetPasswordData = result.data.data
                Result.Success(
                    ResetPasswordModel(
                        success = resetPasswordData.success,
                        message = resetPasswordData.message,
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }
}