package com.dev.smartparking.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.dev.smartparking.R
import com.dev.smartparking.ui.theme.SmartParkingTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {
    LaunchedEffect(
        key1 = Unit
    ) {
        delay(2000)
    }


}

@Composable
fun Splash(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.parking_location_gif_splash_screen)
                .decoderFactory(GifDecoder.Factory())
                .crossfade(true)
                .build(),
            contentDescription = "Splash Animation",
            modifier = Modifier.size(150.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    SmartParkingTheme {
        Splash()
    }
}