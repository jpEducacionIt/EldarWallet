package com.example.eldarwallet.presentation.cardform

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.core.action.SaveNewCreditCard
import com.example.eldarwallet.core.action.ValidateCardholder
import com.example.eldarwallet.core.domain.CardHolderResponse
import com.example.eldarwallet.core.domain.SaveInAppResponse
import com.example.eldarwallet.infrastructure.db.UserDatabase
import com.example.eldarwallet.infrastructure.repository.InAppCreditUserDataRepositoryImp
import com.example.eldarwallet.infrastructure.repository.InJsonCreditUserDataRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class CardFormViewModel(application: Application) : ViewModel() {
    private val database = UserDatabase.getDataBase(application)
    private val jsonUserDataRepository = InJsonCreditUserDataRepositoryImp(application)
    private val roomUserDataRepository = InAppCreditUserDataRepositoryImp(database)
    private val validateCardHolder = ValidateCardholder(jsonUserDataRepository)
    private val saveNewCreditCard = SaveNewCreditCard(roomUserDataRepository)

    private val _userDataVerificationStatus = MutableLiveData<CardHolderResponse>()
    val userDataVerificationStatus: LiveData<CardHolderResponse>
        get() = _userDataVerificationStatus

    private val _navigateToDashboard = MutableLiveData<SaveInAppResponse>()
    val navigateToDashboard: LiveData<SaveInAppResponse>
        get() = _navigateToDashboard

    fun validateCardHolderData(
        surname: String,
        name: String
    ) {
        val actionData = ValidateCardholder.ActionData(surname, name)
        _userDataVerificationStatus.value = validateCardHolder(actionData)
    }

    fun saveNewCreditCard( surname: String,
                           name: String,
                           number: String,
                           expiry: String,
                           cvv: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val actionData = SaveNewCreditCard.ActionData(0, surname, name, number, expiry, cvv)
                saveNewCreditCard(actionData)
                _navigateToDashboard.postValue(SaveInAppResponse.SUCCESS)
            } catch (e: UnknownHostException) {
                _navigateToDashboard.postValue(SaveInAppResponse.ERROR)
            }
        }
    }
}