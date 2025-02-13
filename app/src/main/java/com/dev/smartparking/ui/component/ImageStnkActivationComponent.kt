package com.dev.smartparking.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ImageStnkActivationComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_car_stnk_activation1),
            contentDescription = stringResource(R.string.title_screen_activation),
            modifier = Modifier.fillMaxWidth()
        )

        Image(
            painter = painterResource(id = R.drawable.image_licence_plat_car),
            contentDescription = stringResource(R.string.title_screen_activation),
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .offset(y = (-15).dp, x = 50.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ImageStnkActivation() {
    SmartParkingTheme {
        ImageStnkActivationComponent()
    }
}