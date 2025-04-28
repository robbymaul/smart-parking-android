package com.dev.smartparking.data.api

import com.dev.smartparking.data.model.request.ForgotPasswordRequest
import com.dev.smartparking.data.model.request.ForgotPasswordVerifyOtpRequest
import com.dev.smartparking.data.model.request.LoginRequest
import com.dev.smartparking.data.model.request.LogoutRequest
import com.dev.smartparking.data.model.request.RegisterRequest
import com.dev.smartparking.data.model.request.ResetPasswordRequest
import com.dev.smartparking.data.model.request.SendOTPRequest
import com.dev.smartparking.data.model.request.VerifyOTPRequest
import com.dev.smartparking.data.model.response.ForgotPasswordResponse
import com.dev.smartparking.data.model.response.ForgotPasswordVerifyOtpResponse
import com.dev.smartparking.data.model.response.LoginResponse
import com.dev.smartparking.data.model.response.LogoutResponse
import com.dev.smartparking.data.model.response.RegisterResponse
import com.dev.smartparking.data.model.response.ResetPasswordResponse
import com.dev.smartparking.data.model.response.ResponseData
import com.dev.smartparking.data.model.response.SendOTPResponse
import com.dev.smartparking.data.model.response.VerifyOTPResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<ResponseData<RegisterResponse>>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<ResponseData<LoginResponse>>

    @POST("auth/send-phone-otp")
    suspend fun sendPhoneOTP(@Body sendOTPRequest: SendOTPRequest): Response<ResponseData<SendOTPResponse>>

    @GET("auth/resend-phone-otp")
    suspend fun resendPhoneOTP(): Response<ResponseData<SendOTPResponse>>

    @POST("auth/verify-phone")
    suspend fun verifyOTP(@Body verifyOTPRequest: VerifyOTPRequest): Response<ResponseData<VerifyOTPResponse>>

    @POST("auth/logout")
    suspend fun logout(@Body logoutRequest: LogoutRequest): Response<ResponseData<LogoutResponse>>

    @POST("auth/forgot-password/request")
    suspend fun forgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest): Response<ResponseData<ForgotPasswordResponse>>

    @POST("auth/forgot-password/verify-otp")
    suspend fun forgotPasswordVerifyOtp(@Body forgotPasswordVerifyOtpRequest: ForgotPasswordVerifyOtpRequest): Response<ResponseData<ForgotPasswordVerifyOtpResponse>>

    @POST("auth/reset-password")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): Response<ResponseData<ResetPasswordResponse>>
    // Tambahkan endpoint API lainnya sesuai kebutuhan
}