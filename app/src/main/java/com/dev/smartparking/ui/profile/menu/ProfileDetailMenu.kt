package com.dev.smartparking.ui.profile.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable


data class ProfileDetailMenu(
    val icon: @Composable () -> Unit,
    val label: String,
    val actions: List<ProfileMenuAction> = emptyList()
)

data class ProfileMenuAction(
    val content: @Composable () -> Unit,
    val onClick: () -> Unit = {}
)

