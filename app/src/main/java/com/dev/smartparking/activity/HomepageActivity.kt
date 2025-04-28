package com.dev.smartparking.activity

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dev.smartparking.ui.component.TopBarMenuHomepageComponent
import com.dev.smartparking.ui.screen.HomepageScreen

@Composable
fun HomepageActivity(modifier: Modifier = Modifier) {
    Scaffold (
        modifier = modifier.fillMaxSize(),
        topBar = { TopBarMenuHomepageComponent() }
    ) { innerPadding ->
       HomepageScreen(modifier = Modifier.padding(innerPadding), navController = null)
    }
}