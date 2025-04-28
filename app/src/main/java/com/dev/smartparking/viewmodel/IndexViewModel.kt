package com.dev.smartparking.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class IndexViewModel: ViewModel() {
    // States
    var isOk by mutableStateOf(true)
        private set


    fun onIsOkChange(value: Boolean) {
        isOk = value
    }
}