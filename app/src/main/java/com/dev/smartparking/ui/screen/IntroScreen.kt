package com.dev.smartparking.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.ui.section.IntroSection
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    image: Painter
) {
    Column (
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = description,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(270.dp)
        )
        IntroSection(
            title = title,
            description = description
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroScreenPreview() {
    SmartParkingTheme (dynamicColor = false) {
        IntroScreen(
            title = stringResource(R.string.title_screen_safe_and_secure) ,
            description = stringResource(R.string.desc_screen_easy_parking),
            image = painterResource(R.drawable.smart_parking_logo1)
        )
    }
}