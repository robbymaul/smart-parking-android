package com.dev.smartparking

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.dev.smartparking.ui.navigation.SmartParkingAppEntry
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val locale = Locale.getDefault() // Or specify your preferred locale
        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        createConfigurationContext(config)

        setContent {
            SmartParkingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SmartParkingAppEntry()

                }
            }
        }
    }
}




//@Composable
//fun MyApp(modifier: Modifier = Modifier) {
//    val navController = rememberNavController()
//
//    SmartParkingTheme {
//        AppNavigation(
//            navController = navController
//        )
//    }
//}