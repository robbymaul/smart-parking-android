package com.dev.smartparking.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "accessToken") val accessToken: String,
)

@JsonClass(generateAdapter = true)
data class RegisterResponse(
//    val id: String,
    val username: String,
    val email: String,
    @Json(name = "phoneNumber") val phoneNumber: String,
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
)

@JsonClass(generateAdapter = true)
data class ResponseData<T>(
    val code: Int,
    val status: Boolean,
    val data: T
)

@JsonClass(generateAdapter = true)
data class SendOTPResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "message") val message: String,
    @Json(name = "phoneNumber") val phoneNumber: String,
    @Json(name = "expiryMinutes") val expiryMinutes: Int
)

@JsonClass(generateAdapter = true)
data class VerifyOTPResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "message") val message: String,
    @Json(name = "phoneNumber") val phoneNumber: String,
    @Json(name = "token") val token: String
)

@JsonClass(generateAdapter = true)
data class LogoutResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "message") val message: String,
    @Json(name = "loggedOut") val loggedOut: String
)

@JsonClass(generateAdapter = true)
data class ForgotPasswordResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "message") val message: String,
    @Json(name = "phoneNumber") val phoneNumber: String,
    @Json(name = "expiryMinutes") val expiryMinutes: Int
)

@JsonClass(generateAdapter = true)
data class ForgotPasswordVerifyOtpResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "message") val message: String,
    @Json(name = "phoneNumber") val phoneNumber: String,
    @Json(name = "token") val token: String,
    @Json(name = "validUntil") val validUntil: String
)


@JsonClass(generateAdapter = true)
data class ResetPasswordResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "message") val message: String,
)