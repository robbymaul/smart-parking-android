package com.dev.smartparking.ui.section

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.ui.card.MallCard
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun ContentSection(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    content: @Composable ()->Unit
) {
    Column (
        modifier = modifier
    ) {
        Text(
            stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top =32.dp, bottom = 8.dp)
        )
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun ContentSectionPreview() {
    SmartParkingTheme {
        ContentSection(
            title = R.string.title_screen_login
        ) {
            MallCard(
                imageUrl = "https://res.cloudinary.com/dxdtxld4f/image/upload/v1738768682/skripsi/image_mall1_ixzb6u.jpg",
                rating = "4.5",
                mallName = "Margonda City Mall",
                isLike = true,
                onClickButton = {}
            )
        }
    }
}