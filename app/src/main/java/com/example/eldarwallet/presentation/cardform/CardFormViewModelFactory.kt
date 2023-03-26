package com.example.eldarwallet.presentation.cardform

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eldarwallet.presentation.dashboard.fragments.DashboardViewModel

class CardFormViewModelFactory(
    private val application: Application
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CardFormViewModel(application) as T
    }
}