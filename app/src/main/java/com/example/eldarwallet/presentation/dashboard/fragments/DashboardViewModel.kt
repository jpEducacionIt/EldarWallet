package com.example.eldarwallet.presentation.dashboard.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.infrastructure.representation.UserCardsData
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _userCardsData = MutableLiveData<UserCardsData>()

    val userCardsData: LiveData<UserCardsData>
        get() = _userCardsData

    fun onShow() {
        viewModelScope.launch {

        }
    }
}