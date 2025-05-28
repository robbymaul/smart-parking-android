package com.dev.smartparking.data.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun formatToWIBDateTime(dateStr: String?): String {
    if (dateStr.isNullOrBlank()) return ""

    return try {
        // Hapus Z di belakang
        val cleanStr = dateStr.removeSuffix("Z")
        val localDateTime = LocalDateTime.parse(cleanStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy, HH:mm")
        localDateTime.format(formatter)
    } catch (e: Exception) {
        ""
    }
}
