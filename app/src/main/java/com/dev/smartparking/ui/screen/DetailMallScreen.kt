package com.dev.smartparking.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.R.string.txt_button_book_now
import com.dev.smartparking.ui.card.ImageMallDetailCard
import com.dev.smartparking.ui.component.ButtonComponent
import com.dev.smartparking.ui.component.DetailMallContentComponent
import com.dev.smartparking.ui.component.TopBarMenuHomepageComponent
import com.dev.smartparking.ui.element.MallDirectionButtonElement
import com.dev.smartparking.ui.element.MallDirectionInfoElement
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun DetailMallScreen(modifier: Modifier = Modifier) {
    Scaffold (
        topBar = { TopBarMenuHomepageComponent()}
    ) { innerPadding ->
        Box (
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues = innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                ImageMallDetailCard(
                    imageUrl = "https://res.cloudinary.com/dxdtxld4f/image/upload/v1738768682/skripsi/image_mall1_ixzb6u.jpg",
                )

                DetailMallContentComponent()
            }
            ButtonComponent(
                text = txt_button_book_now,
                textColor = MaterialTheme.colorScheme.background,
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailMallScreenPreview() {
    SmartParkingTheme {
        DetailMallScreen()
    }
}