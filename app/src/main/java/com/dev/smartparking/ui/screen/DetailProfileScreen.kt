package com.dev.smartparking.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dev.smartparking.R
import com.dev.smartparking.data.local.datastore.AuthPreferences
import com.dev.smartparking.domain.model.UserModel
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.component.DialogComponent
import com.dev.smartparking.ui.component.DialogVariant
import com.dev.smartparking.ui.dialog.UpdateVehicleDialog
import com.dev.smartparking.ui.element.FormTextFieldElement
import com.dev.smartparking.ui.section.SectionFormField
import com.dev.smartparking.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.first
import org.koin.compose.koinInject

@Composable()
fun DetailProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    profileViewModel: ProfileViewModel
) {
    var showDialogUpdateVehicle by remember { mutableStateOf(false) }
    val authPreferences = koinInject<AuthPreferences>()

    var name by remember { mutableStateOf("${profileViewModel.userProfileModel?.firstName} ${profileViewModel.userProfileModel?.lastName}") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf(profileViewModel.userProfileModel?.address ?: "") }
    var user by remember { mutableStateOf<UserModel?>(null) }

    LaunchedEffect(Unit) {
        authPreferences.user.first()?.let {
            user = it
            Log.d("triggered auth preferences user", "$user")
            email = it.email
            phoneNumber = it.phoneNumber
        }
    }

    Column(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(16.dp))
                .shadow(4.dp, RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .heightIn(min = 240.dp, max = 240.dp), // Supaya tidak terlalu tinggi statis
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                AsyncImage(
                    model = profileViewModel.userProfileModel?.profilePhoto,
                    contentDescription = "use profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    error = painterResource(R.drawable.image_default_avatar1),
                    onSuccess = { Log.d("ImageLoad", "Image loaded successfully") },
                    onError = { Log.e("ImageLoad", "Error loading image: ${it.result.throwable}") }
                )

                SectionFormField(
                    title = stringResource(R.string.txt_title_field_name1),
                    textStyle = MaterialTheme.typography.titleMedium
                ) {
                    FormTextFieldElement(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        placeHolder = stringResource(R.string.txt_title_field_name1),
                        value = name,
                        onValueChange = { value -> name = value },
                    )
                }

                SectionFormField(
                    title = stringResource(R.string.txt_title_field_email1),
                    textStyle = MaterialTheme.typography.titleMedium
                ) {
                    FormTextFieldElement(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        placeHolder = stringResource(R.string.txt_title_field_email1),
                        value = email,
                        onValueChange = { value -> email = value },
                    )
                }

                SectionFormField(
                    title = stringResource(R.string.txt_title_field_phone_number),
                    textStyle = MaterialTheme.typography.titleMedium
                ) {
                    FormTextFieldElement(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        placeHolder = stringResource(R.string.txt_title_field_phone_number),
                        value = phoneNumber,
                        onValueChange = { value -> phoneNumber = value },
                    )
                }

                SectionFormField(
                    title = stringResource(R.string.txt_title_field_address1),
                    textStyle = MaterialTheme.typography.titleMedium
                ) {
                    FormTextFieldElement(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        placeHolder = stringResource(R.string.txt_title_field_address1),
                        value = address,
                        onValueChange = { value -> address = value },
                    )
                }
            }
        }
    }
}