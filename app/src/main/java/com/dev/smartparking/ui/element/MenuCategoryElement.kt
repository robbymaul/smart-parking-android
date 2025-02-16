package com.dev.smartparking.ui.element

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.ui.theme.SmartParkingTheme

@Composable
fun MenuCategoryElement(modifier: Modifier = Modifier) {
    Surface (
        modifier = modifier.width(165.dp)
            .heightIn(min = 89.dp),
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {},
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                ),
                modifier = Modifier.fillMaxWidth()
                    .heightIn(min = 68.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.image_mall_button1),
                    contentDescription = stringResource(R.string.txt_mall1),
                    modifier = Modifier.size(48.dp)
                )
            }
            Text(
                text = stringResource(R.string.txt_mall1),
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.paddingFromBaseline(top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuCategoryElementPreview() {
    SmartParkingTheme {
        MenuCategoryElement()
    }
}