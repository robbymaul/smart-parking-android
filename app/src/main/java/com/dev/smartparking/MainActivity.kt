package com.dev.smartparking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.smartparking.ui.card.MallCard
import com.dev.smartparking.ui.component.TextFieldOtpComponent
import com.dev.smartparking.ui.element.TextFieldOtpElement
import com.dev.smartparking.ui.screen.Splash
import com.dev.smartparking.ui.theme.SmartParkingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartParkingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    MallCard(
//                        modifier = Modifier.padding(innerPadding),
//                        imageUrl = "https://res.cloudinary.com/dxdtxld4f/image/upload/v1738768682/skripsi/image_mall1_ixzb6u.jp",
//                        mallName = "Margonda City Mall",
//                        rating = "4.5",
//                        isLike = false
//                    )

                    Splash(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}