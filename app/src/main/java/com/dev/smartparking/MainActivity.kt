package com.dev.smartparking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.dev.smartparking.ui.element.FormTextFieldElement
import com.dev.smartparking.ui.screen.Splash
import com.dev.smartparking.ui.section.SectionFormField
import com.dev.smartparking.ui.theme.SmartParkingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartParkingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    MallCard(
//                        modifier = Modifier.padding(innerPadding),
//                        imageUrl = "https://res.cloudinary.com/dxdtxld4f/image/upload/v1738768682/skripsi/image_mall1_ixzb6u.jp",
//                        mallName = "Margonda City Mall",
//                        rating = "4.5",
//                        isLike = false
//                    )

//                    Splash(modifier = Modifier.padding(innerPadding))
                    SectionFormField(
                        modifier = Modifier.padding(paddingValues = innerPadding),
                        title = R.string.txt_title_field_password1,
                        textStyle = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 12.sp
                        )
                    ) {
                        var passwordVisible by remember { mutableStateOf(false) }

                        FormTextFieldElement(
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            placeHolder = R.string.txt_place_holder_form_password,
                            value = "asdasdasd",
                            onValueChange = {},
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                val image = if (passwordVisible) Icons.Default.Visibility else Icons.Filled.VisibilityOff
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = image,
                                        contentDescription = "Toggle Password Visibility"
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}