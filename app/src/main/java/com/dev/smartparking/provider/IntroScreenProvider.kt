package com.dev.smartparking.provider

import com.dev.smartparking.R
import com.dev.smartparking.data.IntroScreenData

object IntroScreenProvider {
    fun getIntroScreens(): List<IntroScreenData> {
        return listOf(
            IntroScreenData(
                title = R.string.title_screen_easy_parking,
                description = R.string.desc_screen_easy_parking,
                image = R.drawable.image_easy_parking1
            ),
            IntroScreenData(
                title = R.string.title_screen_book_anytime_anywhere,
                description = R.string.desc_screen_book_anytime_anywhere,
                image = R.drawable.image_book_anytime_anywhere
            ),
            IntroScreenData(
                title = R.string.title_screen_safe_and_secure,
                description = R.string.desc_screen_safe_and_secure,
                image = R.drawable.image_safe_and_secure1
            )
        )
    }
}