package com.dev.smartparking.ui.element

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.smartparking.R
import com.dev.smartparking.data.utils.callPhone
import com.dev.smartparking.data.utils.openGoogleMaps
import com.dev.smartparking.ui.component.ButtonDirectionComponent
import com.dev.smartparking.ui.theme.SmartParkingTheme
import com.dev.smartparking.viewmodel.DetailMallViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MallDirectionButtonElement(
    modifier: Modifier = Modifier,
    detailMallViewModel: DetailMallViewModel
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ButtonDirectionComponent(
                text = stringResource(R.string.txt_login),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Directions,
                        contentDescription = ""
                    )
                }
            ) {
                openGoogleMaps(
                    context = context,
                    longitude = detailMallViewModel.detailPlaceModel?.longitude ?: 0.0,
                    latitude = detailMallViewModel.detailPlaceModel?.latitude ?: 0.0
                )
            }
            ButtonDirectionComponent(
                text = stringResource(R.string.txt_login),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = ""
                    )
                }
            ) {
                callPhone(context = context, phone = detailMallViewModel.detailPlaceModel?.contactNumber ?: "")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MallDirectionButtonElementPreview() {
    SmartParkingTheme {
        MallDirectionButtonElement(detailMallViewModel = koinViewModel())
    }
}