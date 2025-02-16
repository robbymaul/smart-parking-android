package com.dev.smartparking.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.ui.element.MenuCategoryElement
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun MenuCategoriesComponent(modifier: Modifier = Modifier) {
    LazyRow (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(19) {
            MenuCategoryElement()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuCategoriesComponentPreview() {
    SmartParkingTheme {
        MenuCategoriesComponent()
    }
}