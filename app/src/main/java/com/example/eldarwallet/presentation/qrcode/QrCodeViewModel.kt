package com.example.eldarwallet.presentation.qrcode

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eldarwallet.core.action.GetInJsonUserData
import com.example.eldarwallet.infrastructure.repository.InJsonCreditUserDataRepositoryImp
import com.example.eldarwallet.infrastructure.representation.UserVerificationData

class QrCodeViewModel(application: Application) : ViewModel() {
    private val jsonUserDataRepository = InJsonCreditUserDataRepositoryImp(application)
    private val getUserData = GetInJsonUserData(jsonUserDataRepository)

    private val _userData = MutableLiveData<UserVerificationData>()
    val userData: LiveData<UserVerificationData>
        get() = _userData

    init {
        _userData.value = getUserData()
    }
}
