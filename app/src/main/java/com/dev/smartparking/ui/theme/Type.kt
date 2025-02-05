package com.dev.smartparking.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with

//val Poppins = FontFamily(
//    Font(R.font.poppins_regular, FontWeight.Normal),
//    Font(R.font.poppins_bold, FontWeight.Bold),
//    Font(R.font.poppins_semibold, FontWeight.SemiBold),
//    Font(R.font.poppins_light, FontWeight.Light),
//)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
//    bodyMedium = TextStyle(
//        fontFamily = Poppins,
//        fontWeight = FontWeight.SemiBold,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    ),
//    titleLarge = TextStyle(
//        fontFamily = Poppins,
//        fontWeight = FontWeight.Bold,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//    ),
//    titleMedium = TextStyle(
//        fontFamily = Poppins,
//        fontWeight = FontWeight.SemiBold,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//    ),
//    headlineMedium = TextStyle(
//        fontFamily = Poppins,
//        fontWeight = FontWeight.Light,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)