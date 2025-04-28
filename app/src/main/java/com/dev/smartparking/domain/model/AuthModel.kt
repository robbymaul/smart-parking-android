package com.dev.smartparking.domain.model

import com.squareup.moshi.Json

data class RegisterUser(
    val username: String,
    val email: String,
    val phoneNumber: String,
    val firstName: String,
    val lastName: String,
//    val isLoggedIn: Boolean = false
)

data class LoginUser(
    val accessToken: String
)

data class SendPhoneOtpModel(
    val success: Boolean,
    val message: String,
    val phoneNumber: String,
    val expiryMinutes: Int
)

data class VerifyOTPModel(
    val success: Boolean,
    val message: String,
    val phoneNumber: String,
    val token: String
)

data class LogoutModel(
    val success: Boolean,
    val message: String,
    val loggedOut: String
)

data class ForgotPasswordModel(
    val success: Boolean,
    val message: String,
    val phoneNumber: String,
    val expiryMinutes: Int
)

data class ForgotPasswordVerifyOtpModel(
    val success: Boolean,
    val message: String,
    val phoneNumber: String,
    val token: String,
    val validUntil: String
)

data class ResetPasswordModel(
    val success: Boolean,
    val message: String,
)