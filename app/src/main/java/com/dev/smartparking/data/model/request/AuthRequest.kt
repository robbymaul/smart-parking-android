package com.dev.smartparking.data.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "usernameOrEmailOrPhoneNumber") val phoneNumber: String,
    @Json(name = "password") val password: String
)

@JsonClass(generateAdapter = true)
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    @Json(name = "phoneNumber") val phoneNumber: String,
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String
)

@JsonClass(generateAdapter = true)
data class SendOTPRequest(
    @Json(name = "phoneNumber") val phoneNumber: String,
)

@JsonClass(generateAdapter = true)
data class VerifyOTPRequest(
    @Json(name = "otp") val otp: String,
)

@JsonClass(generateAdapter = true)
data class LogoutRequest(
    @Json(name = "refreshToken") val refreshToken: String
)

@JsonClass(generateAdapter = true)
data class ForgotPasswordRequest(
    @Json(name = "phoneNumber") val phoneNumber: String
)

@JsonClass(generateAdapter = true)
data class UpdatePasswordRequest(
    @Json(name = "oldPassword") val oldPassword: String,
    @Json(name = "newPassword") val newPassword: String,
    @Json(name = "confirmNewPassword") val confirmNewPassword: String,
)

@JsonClass(generateAdapter = true)
data class ForgotPasswordVerifyOtpRequest(
    @Json(name = "phoneNumber") val phoneNumber: String,
    @Json(name = "otp") val otp: String
)

@JsonClass(generateAdapter = true)
data class ResetPasswordRequest(
    @Json(name = "phoneNumber") val phoneNumber: String,
    @Json(name = "token") val token: String,
    @Json(name = "newPassword") val password: String,
    @Json(name = "confirmPassword") val confirmPassword: String
)