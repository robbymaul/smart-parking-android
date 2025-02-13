package com.dev.smartparking.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ImageButtonComponent(
    modifier: Modifier = Modifier,
    @DrawableRes painter: Int,
    desc: String = ""
) {
    Image(
        painter = painterResource(painter),
        contentScale = ContentScale.Crop,
        contentDescription = desc,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun ImageButtonComponentPreview() {
    SmartParkingTheme {
        ImageButtonComponent(
            painter = R.drawable.image_logo_facebook1,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .clip(CircleShape)
                .clickable {

                }
        )
    }
}