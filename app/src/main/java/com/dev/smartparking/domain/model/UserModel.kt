package com.dev.smartparking.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserModel (
    val username: String,
    val email: String,
    val phoneNumber: String,
    val accountType: String,
    val emailVerified: Boolean,
    val phoneVerified: Boolean,
    val accountStataus: String,
    val isVehicleActivated: Boolean
)