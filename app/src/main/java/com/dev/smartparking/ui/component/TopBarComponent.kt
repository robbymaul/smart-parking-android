package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.theme.SmartParkingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(
    modifier: Modifier = Modifier,
    title: String = "",
    onClickIcon: ()->Unit,
    actions: @Composable ()-> Unit = {
        Spacer(
            modifier = Modifier.size(40.dp)
        )
    },
    navigationIcon: (@Composable ()-> Unit)? = null
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)),
        title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                    )
                )
        },
        navigationIcon = navigationIcon ?: {},
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.background,
            actionIconContentColor = MaterialTheme.colorScheme.secondary,
        ),
        actions = {
            actions()
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TopBarComponentPreview() {
    SmartParkingTheme {
        Scaffold (
            topBar = { TopBarComponent(
                title = "Ticket",
                onClickIcon = {}
            ) }
        ) { innerPadding ->
            Column (
                modifier = Modifier.padding(innerPadding).fillMaxSize()
            ) {

            }
        }
    }
}