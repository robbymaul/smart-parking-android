package com.dev.smartparking.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dev.smartparking.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_preferences")

class AuthPreferences(private val context: Context) {

    companion object {
        private val AUTH_TOKEN = stringPreferencesKey("auth_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val USER_PHONE_NUMBER = stringPreferencesKey("user_phone_number")
        private val USER_ID = stringPreferencesKey("user_id")
        private val USERNAME = stringPreferencesKey("username")
        private val USER = stringPreferencesKey("user")
    }

    // Menggambil phone number saat berhasil login
    val userPhoneNumber: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_PHONE_NUMBER] ?: ""
    }

    // Mengambil token autentikasi
    val authToken: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[AUTH_TOKEN] ?: ""
    }

    // Mengambil token autentikasi
    val refreshToken: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[REFRESH_TOKEN] ?: ""
    }

    // Mengambil user ID
    val userId: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_ID] ?: ""
    }

    // Mengambil username
    val username: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USERNAME] ?: ""
    }

    val user: Flow<UserModel?> = context.dataStore.data.map { preferences ->
        preferences[USER]?.let {
            // Menggunakan decodeFromString untuk deserialisasi
            Json.decodeFromString<UserModel>(it)
        }
    }

    // Menyimpan data user
    suspend fun saveUser(user: UserModel) {
        context.dataStore.edit { preferences ->
            // Menggunakan encodeToString untuk serialisasi
            preferences[USER] = Json.encodeToString(user)  // Serialize objek ke JSON
        }
    }

    // Menyimpan user phone number
    suspend fun savePhoneNumber(phoneNumber: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PHONE_NUMBER] = phoneNumber
        }
    }

    // Menyimpan token autentikasi
    suspend fun saveAuthToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[AUTH_TOKEN] = token
        }
    }

    suspend fun saveRefreshAuthToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN] = token
        }
    }

    // Menyimpan user ID
    suspend fun saveUserId(id: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = id
        }
    }

    // Menyimpan username
    suspend fun saveUsername(username: String) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    // Menghapus token saat logout
    suspend fun clearAuthToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(AUTH_TOKEN)
        }
    }

    // Menghapus semua data user
    suspend fun clearUserData() {
        context.dataStore.edit { preferences ->
            preferences.remove(AUTH_TOKEN)
            preferences.remove(REFRESH_TOKEN)
            preferences.remove(USER_PHONE_NUMBER)
            preferences.remove(USER_ID)
            preferences.remove(USERNAME)
        }
    }
}