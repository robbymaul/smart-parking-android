package com.dev.smartparking.data

data class LoginCredentialData(
    var phoneNumber: String = "",
    var password: String = ""
) {
    fun isNotEmpty(): Boolean {
        return phoneNumber.isNotBlank() && phoneNumber.isNotEmpty()
    }

    fun isNotBlank(): Boolean {
        return phoneNumber.isNotBlank() && phoneNumber.isNotBlank()
    }
}