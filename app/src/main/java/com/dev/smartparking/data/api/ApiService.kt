package com.dev.smartparking.data.api

import com.dev.smartparking.data.model.request.CreateBookingRequest
import com.dev.smartparking.data.model.request.CreateVehiclesRequest
import com.dev.smartparking.data.model.request.ForgotPasswordRequest
import com.dev.smartparking.data.model.request.ForgotPasswordVerifyOtpRequest
import com.dev.smartparking.data.model.request.LoginRequest
import com.dev.smartparking.data.model.request.LogoutRequest
import com.dev.smartparking.data.model.request.PaymentRequest
import com.dev.smartparking.data.model.request.RegisterRequest
import com.dev.smartparking.data.model.request.ResetPasswordRequest
import com.dev.smartparking.data.model.request.SendOTPRequest
import com.dev.smartparking.data.model.request.UpdatePasswordRequest
import com.dev.smartparking.data.model.request.VerifyOTPRequest
import com.dev.smartparking.data.model.response.BookingResponse
import com.dev.smartparking.data.model.response.CreateBookingResponse
import com.dev.smartparking.data.model.response.CreateVehiclesResponse
import com.dev.smartparking.data.model.response.ForgotPasswordResponse
import com.dev.smartparking.data.model.response.ForgotPasswordVerifyOtpResponse
import com.dev.smartparking.data.model.response.GetVehiclesResponse
import com.dev.smartparking.data.model.response.LoginResponse
import com.dev.smartparking.data.model.response.LogoutResponse
import com.dev.smartparking.data.model.response.ParkingSlotResponse
import com.dev.smartparking.data.model.response.ParkingZoneResponse
import com.dev.smartparking.data.model.response.PaymentMethodResponse
import com.dev.smartparking.data.model.response.PaymentResponse
import com.dev.smartparking.data.model.response.PaymentStatusResponse
import com.dev.smartparking.data.model.response.PlacesRatingResponse
import com.dev.smartparking.data.model.response.PlacesResponse
import com.dev.smartparking.data.model.response.RegisterResponse
import com.dev.smartparking.data.model.response.ResetPasswordResponse
import com.dev.smartparking.data.model.response.ResponseData
import com.dev.smartparking.data.model.response.SendOTPResponse
import com.dev.smartparking.data.model.response.TicketBookingResponse
import com.dev.smartparking.data.model.response.UpdatePasswordResponse
import com.dev.smartparking.data.model.response.UserProfileResponse
import com.dev.smartparking.data.model.response.UserResponse
import com.dev.smartparking.data.model.response.VerifyOTPResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // auth
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

    // users
    @GET("users")
    suspend fun getUser(): Response<ResponseData<UserResponse>>
    @GET("users/profile")
    suspend fun getUserProfile(): Response<ResponseData<UserProfileResponse>>
    @POST("users/password")
    suspend fun updateUserPassword(@Body updatePasswordRequest: UpdatePasswordRequest): Response<ResponseData<UpdatePasswordResponse>>
    @POST("users/vehicles")
    suspend fun createVehicles(@Body createVehiclesRequest: CreateVehiclesRequest): Response<ResponseData<CreateVehiclesResponse>>
    @GET("users/vehicles")
    suspend fun getUserVehicles(): Response<ResponseData<List<GetVehiclesResponse>>>
    @PUT("users/vehicles/{id}")
    suspend fun updateUserVehicles(@Path("id") id: Int, @Body updateVehiclesRequest: GetVehiclesResponse): Response<ResponseData<GetVehiclesResponse>>

    // place
    @GET("places")
    suspend fun getPlaces(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("search") search: String = "",
        @Query("city") city: String = "",
        @Query("area") area: String = "",
    ): Response<ResponseData<List<PlacesResponse>>>
    @GET("places/{id}")
    suspend fun getDetailPlaces(@Path("id") mallId: Int): Response<ResponseData<PlacesResponse>>
    @GET("places/{id}/ratings")
    suspend fun getPlacesRatings(
        @Path("id") mallId: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<ResponseData<List<PlacesRatingResponse>>>
    @GET("places/{id}/parking-zone")
    suspend fun getListParkingZone(@Path("id") placeId: Int): Response<ResponseData<List<ParkingZoneResponse>>>
    @GET("parking-zone/{id}/parking-slot")
    suspend fun getListParkingSlot(@Path("id") zoneId: Int): Response<ResponseData<List<ParkingSlotResponse>>>

    // booking
    @POST("bookings")
    suspend fun createBooking(@Body createBookingRequest: CreateBookingRequest): Response<ResponseData<CreateBookingResponse>>
    @GET("bookings/{id}")
    suspend fun getBooking(@Path("id") bookingId: Int): Response<ResponseData<BookingResponse>>
    @GET("tickets")
    suspend fun getListTicketBooking(): Response<ResponseData<List<TicketBookingResponse>>>

    // payment
    @GET("payments/method")
    suspend fun paymentMethod(): Response<ResponseData<List<PaymentMethodResponse>>>
    @POST("payments/{id}")
    suspend fun payment(@Path("id") bookingId: Int, paymentRequest: PaymentRequest): Response<ResponseData<PaymentResponse>>
    @GET("payments/{id}/status")
    suspend fun checkPayment(@Path("id") bookingId: Int): Response<ResponseData<PaymentStatusResponse>>
    // Tambahkan endpoint API lainnya sesuai kebutuhan
}