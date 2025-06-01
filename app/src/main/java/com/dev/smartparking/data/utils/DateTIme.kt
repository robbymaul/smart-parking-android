package com.dev.smartparking.data.utils

import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
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

fun extractTimeOnly(isoTime: String): String {
    return try {
        val dateTime = OffsetDateTime.parse(isoTime)
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return dateTime.format(formatter)
    } catch (e: Exception) {
        ""
    }
}

fun getHourDifference(startTime: String, endTime: String): Long {
    val start = OffsetDateTime.parse(startTime)
    val end = OffsetDateTime.parse(endTime)
    return Duration.between(start, end).toHours()
}