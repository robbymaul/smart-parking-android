package com.dev.smartparking.data.repository

import android.util.Log
import com.dev.smartparking.data.api.ApiService
import com.dev.smartparking.data.local.datastore.AuthPreferences
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
import com.dev.smartparking.data.utils.ErrorUtils
import com.dev.smartparking.data.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlin.random.Random

class AuthRepository(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences
) {
    suspend fun register(
        email: String,
        password: String,
        phoneNumber: String,
        firstName: String,
        lastName: String
    ): Result<ResponseData<RegisterResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = RegisterRequest(
                    username = "${firstName}${lastName}${
                        Random.nextInt(
                            until = 1000000,
                            from = 1
                        )
                    }",
                    email = email,
                    password = password,
                    phoneNumber = "+62${phoneNumber}",
                    firstName = firstName,
                    lastName = lastName
                )

                val response = apiService.register(request)

                if (response.isSuccessful) {
                    response.body()?.let { registerResponse ->
                        // Jika registrasi berhasil dan response memberikan token, simpan token
//                        registerResponse.data?.token?.let { token ->
//                            authPreferences.saveAuthToken(token)
//                        }
                        Result.Success(registerResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("register request", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun login(phoneNumber: String, password: String): Result<ResponseData<LoginResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = LoginRequest(
                    phoneNumber = "+62$phoneNumber",
                    password = password
                )

                val response = apiService.login(request)

                if (response.isSuccessful) {
                    response.body()?.let { loginResponse ->
                        // Simpan token jika login berhasil
                        loginResponse.data.accessToken.let { token ->
                            authPreferences.saveAuthToken(token)
                            authPreferences.savePhoneNumber(request.phoneNumber)
                        }
                        Result.Success(loginResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("login error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun sendPhoneOTP(phoneNumber: String): Result<ResponseData<SendOTPResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = SendOTPRequest(
                    phoneNumber = phoneNumber,
                )

                val response = apiService.sendPhoneOTP(request)

                if (response.isSuccessful) {
                    response.body()?.let { sendOtpResponse ->
                        // Simpan token jika login berhasil
                        sendOtpResponse.data.let { data ->
                            authPreferences.savePhoneNumber(data.phoneNumber)
                        }
                        Result.Success(sendOtpResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("send otp error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun resendPhoneOTP(): Result<ResponseData<SendOTPResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.resendPhoneOTP()

                if (response.isSuccessful) {
                    response.body()?.let { resendOtpResponse ->
                        // Simpan token jika login berhasil
                        resendOtpResponse.data.let { data ->
                            authPreferences.savePhoneNumber(data.phoneNumber)
                        }
                        Result.Success(resendOtpResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("resend otp error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun verifyOTP(otp: String): Result<ResponseData<VerifyOTPResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = VerifyOTPRequest(otp = otp)
                val response = apiService.verifyOTP(request)

                if (response.isSuccessful) {
                    response.body()?.let { verifyOTPResponse ->
                        // Simpan token jika login berhasil
                        verifyOTPResponse.data.let { data ->
                            authPreferences.savePhoneNumber(data.phoneNumber)
                            authPreferences.saveAuthToken(data.token)
                            authPreferences.saveRefreshAuthToken(data.token)
                        }
                        Result.Success(verifyOTPResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("verify otp error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun logout(refreshToken: String): Result<ResponseData<LogoutResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = LogoutRequest(refreshToken = refreshToken)
                val response = apiService.logout(request)

                if (response.isSuccessful) {
                    response.body()?.let { logoutResponse ->
                        Result.Success(logoutResponse)
                    } ?: Result.Error(Exception("Response body is empty"))
                } else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("logout error", "$e")
                Result.Error(e)
            }
        }
//        authPreferences.clearAuthToken()
    }

    suspend fun forgotPassword(phoneNumber: String): Result<ResponseData<ForgotPasswordResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = ForgotPasswordRequest(phoneNumber = phoneNumber)
                val response  = apiService.forgotPassword(request)

                if (response.isSuccessful) {
                    response.body()?.let { forgotPassword ->
                        Result.Success(forgotPassword)
                    } ?: Result.Error(Exception("Response body is empty"))
                }  else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("forgot password request error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun forgotPasswordVerifyOtp(phoneNumber: String, otp: String): Result<ResponseData<ForgotPasswordVerifyOtpResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = ForgotPasswordVerifyOtpRequest(phoneNumber = phoneNumber, otp = otp)
                val response  = apiService.forgotPasswordVerifyOtp(request)

                if (response.isSuccessful) {
                    response.body()?.let { forgotPasswordVerifyOtp ->
                        Result.Success(forgotPasswordVerifyOtp)
                    } ?: Result.Error(Exception("Response body is empty"))
                }  else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("forgot password verify otp request error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun resetPassword(phoneNumber: String, token: String, password: String, confirmPassword: String): Result<ResponseData<ResetPasswordResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = ResetPasswordRequest(phoneNumber = phoneNumber, token = token, password = password, confirmPassword = confirmPassword)
                val response  = apiService.resetPassword(request)

                if (response.isSuccessful) {
                    response.body()?.let { resetPassword ->
                        Result.Success(resetPassword)
                    } ?: Result.Error(Exception("Response body is empty"))
                }  else {
                    val error = ErrorUtils.parseErrorApi(response.errorBody())
                    Log.d("error response", "${error?.message}")
                    Result.Error(Exception(error?.message ?: "Something went wrong"))
                }
            } catch (e: Exception) {
                Log.e("reset password request error", "$e")
                Result.Error(e)
            }
        }
    }

    suspend fun isLoggedIn(): Boolean {
        return authPreferences.authToken.first().isNotEmpty()
    }
}