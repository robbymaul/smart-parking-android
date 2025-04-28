package com.dev.smartparking.data.utils

import android.util.Log
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody

@JsonClass(generateAdapter = true)
data class ApiError(
    val message: String,
    val error: String,
    val statusCode: Int,

)

object ErrorUtils {
    fun parseErrorApi(errorBody: ResponseBody?): ApiError? {
        return try {
            errorBody?.let {
                val rawError = it.string()  // Menyimpan raw JSON
                Log.d("API_ERROR", "Raw error body: $rawError")  // Cetak raw JSON

                // Inisialisasi Moshi dengan KotlinJsonAdapterFactory untuk kelas data Kotlin
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val adapter = moshi.adapter(ApiError::class.java)

                // Parse JSON ke dalam objek ApiError
                val parsedError = adapter.fromJson(rawError)
                Log.d("API_ERROR", "Parsed error: $parsedError")  // Log hasil parsing
                parsedError
            }
        } catch (e: Exception) {
            Log.e("ErrorUtils", "Error parsing error response: ${e.localizedMessage}")
            null
        }
    }
}