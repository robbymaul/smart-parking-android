package com.dev.smartparking.ui.section

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.smartparking.R
import com.dev.smartparking.ui.screen.IntroScreen
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun IntroSection(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @StringRes description: Int
) {
    Column (
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(title),
            modifier = Modifier.paddingFromBaseline(top = 48.dp, bottom = 16.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
            fontSize = 30.sp
            )
        )
        Text(
            text = stringResource(description),
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.secondary,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroSectionPreview() {
    SmartParkingTheme (dynamicColor = false) {
        IntroSection(
            title = R.string.title_screen_easy_parking,
            description = R.string.desc_screen_easy_parking,
        )
    }
}