package com.dev.smartparking.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.smartparking.data.STNKActivationData
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class STNKActivationViewModel: ViewModel() {
    var stnk by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String>("")
        private set

    var stnkActivationData by mutableStateOf(STNKActivationData())
        private set

    fun onChanged(value: String) {
        this.stnk = value
        stnkActivationData.stnk = this.stnk
    }


    private fun formatSTNK(raw: String): String {
        val cleaned = raw.filter { !it.isWhitespace() }.uppercase()

        // Regex untuk mencocokkan format:
        // Group 1: Karakter pertama (wajib huruf)
        // Group 2: Karakter kedua (opsional, huruf atau digit)
        // Group 3: 1 sampai 4 digit
        // Group 4: 0 sampai 4 huruf (opsional)
        val regex = Regex("^([A-Z])([A-Z0-9]?)([0-9]{1,4})([A-Z]{0,4})$")
        val match = regex.find(cleaned)

        return if (match != null) {
            // Ambil group values
            val prefix = match.groupValues[1] + match.groupValues[2]
            val digits = match.groupValues[3]
            val suffix = match.groupValues[4]
            if (suffix.isNotEmpty()) {
                "$prefix $digits $suffix"
            } else {
                "$prefix $digits"
            }
        } else {
            // Jika format tidak sesuai, kembalikan string asli atau pesan error
            raw
        }
    }

    fun getFormattedSTNK(): String {
       return formatSTNK(stnk)
    }

    fun isValid(): Boolean {
        val cleaned = stnk.filter { !it.isWhitespace() }.uppercase()
        val regex = Regex("^([A-Z])([A-Z0-9]?)([0-9]{1,4})([A-Z]{0,4})$")
        return regex.matches(cleaned)
    }

    fun submit(onSuccess: ()-> Unit) {
        viewModelScope.launch {
            isLoading = true
            delay(2000)

            if (stnkActivationData.stnk.isNotBlank()) {
                if (isValid()) {
                    onSuccess()
                } else {
                    errorMessage = "Invalid STNK Format"
                }
            } else {
                errorMessage = "Please Enter Phone and Password"
            }
        }
    }
}