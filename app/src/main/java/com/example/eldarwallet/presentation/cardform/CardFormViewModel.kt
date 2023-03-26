package com.example.eldarwallet.presentation.cardform

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.core.action.ValidateCardholder
import com.example.eldarwallet.core.domain.CardHolderResponse
import com.example.eldarwallet.infrastructure.repository.InJsonUserDataRepositoryImp
import kotlinx.coroutines.launch

class CardFormViewModel(application: Application) : ViewModel() {
    private val jsonUserDataRepository = InJsonUserDataRepositoryImp(application)
    private val validateCardHolder = ValidateCardholder(jsonUserDataRepository)

    private val _userDataVerificationStatus = MutableLiveData<CardHolderResponse>()
    val userDataVerificationStatus: LiveData<CardHolderResponse>
        get() = _userDataVerificationStatus

    fun onShow(
        surname: String,
        name: String
    ) {
        val actionData = ValidateCardholder.ActionData(surname, name)
        _userDataVerificationStatus.value = validateCardHolder(actionData)
    }

    fun saveNewCreditCard(cardNumber: String,
                          surname: String,
                          name: String,
                          expiry: String,
                          cvv: String) {
        val actionData = ValidateCardholder.ActionData(surname, name)

    }
}