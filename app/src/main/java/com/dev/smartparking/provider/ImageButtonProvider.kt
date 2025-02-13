package com.dev.smartparking.provider

import com.dev.smartparking.R
import com.dev.smartparking.data.ImageButtonData

object ImageButtonProvider {
    fun getListImageButton(): List<ImageButtonData> {
        return listOf(
            R.drawable.image_logo_google1 to {},
            R.drawable.image_logo_facebook1 to {}
        ).map { (ImageButtonData(drawable = it.first, onClick = it.second)) }
    }
}