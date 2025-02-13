package com.dev.smartparking.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.provider.ImageButtonProvider
import com.dev.smartparking.ui.theme.SmartParkingTheme


@Composable
fun SocialLoginButton(modifier: Modifier = Modifier) {
    val imageButtons = ImageButtonProvider.getListImageButton()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        imageButtons.forEachIndexed { index, buttonData ->
            ImageButtonComponent(
                painter = buttonData.drawable,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape)
                    .clickable { buttonData.onClick() }
            )
            if (index < imageButtons.size - 1) {
                Spacer(modifier = Modifier.size(24.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SocialLoginButtonPreview() {
    SmartParkingTheme {
        SocialLoginButton()
    }
}