package com.example.eldarwallet.presentation.dashboard.fragments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.core.action.GetUserVerificationData
import com.example.eldarwallet.infrastructure.repository.InJsonUserDataRepositoryImp
import com.example.eldarwallet.infrastructure.representation.UsersData
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application) : ViewModel() {

    private val _userVerificationData = MutableLiveData<UsersData>()
    val repository = InJsonUserDataRepositoryImp(application)
    val getUserVerificationData = GetUserVerificationData(repository)

    val userVerificationData: LiveData<UsersData>
        get() = _userVerificationData

    fun onShow() {
        viewModelScope.launch {
            _userVerificationData.value = getUserVerificationData()
        }
    }
}
