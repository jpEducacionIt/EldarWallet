package com.example.eldarwallet.presentation.qrcode

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QrCodeViewModelFactory (
    private val application: Application
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QrCodeViewModel(application) as T
    }
}