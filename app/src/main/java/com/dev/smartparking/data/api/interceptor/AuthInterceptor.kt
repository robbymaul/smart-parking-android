package com.dev.smartparking.data.api.interceptor

import com.dev.smartparking.data.local.datastore.AuthPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthInterceptor : Interceptor, KoinComponent {
    private val authPreferences: AuthPreferences by inject()

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Tidak perlu menambahkan token untuk endpoint auth (login, register)
        val authEndpoints = listOf("login", "register", "forgot-password", "reset-password")
        if (authEndpoints.any { originalRequest.url.encodedPath.contains(it) }) {
            val newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Basic cmFoYXNpYTpyYWhhc2lh")
                .build()
            return chain.proceed(newRequest)
        }

        // Mengambil token dari UserPreferences
        val token = runBlocking {
            authPreferences.authToken.first()
        }

        // Jika token tersedia, tambahkan ke header
        return if (token.isNotEmpty()) {
            val newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        } else {
            chain.proceed(originalRequest)
        }
    }
}