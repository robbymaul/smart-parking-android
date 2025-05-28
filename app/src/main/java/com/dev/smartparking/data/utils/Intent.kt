package com.dev.smartparking.data.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext

fun openGoogleMaps(context: Context, latitude: Double, longitude: Double, isNavigation: Boolean = false) {
//    val gmmIntentUri = Uri.parse("google.navigation:q=$latitude,$longitude")
//    val gmmIntentUri = Uri.parse("google.navigation:q=-6.2242681,106.7982943")
    val gmmIntentUri = if (isNavigation) {
        Uri.parse("google.navigation:q=$latitude,$longitude")
    } else {
        Uri.parse("https://www.google.com/maps/search/?api=1&query=$latitude,$longitude")
    }
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
        setPackage("com.google.android.apps.maps")
    }
    if (mapIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(mapIntent)
    } else {
        // fallback ke browser
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.google.com/maps/search/?api=1&query=$latitude,$longitude")
        )
        context.startActivity(browserIntent)
    }
}

fun callPhone(context: Context, phone: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phone")
    }
    context.startActivity(intent)
}