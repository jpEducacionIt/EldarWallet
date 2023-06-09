package com.example.eldarwallet.presentation.dashboard.fragments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.eldarwallet.core.action.GetAllCreditCardsData
import com.example.eldarwallet.core.action.GetCreditCardsDataDecrypt
import com.example.eldarwallet.core.action.UserDataDecrypt
import com.example.eldarwallet.core.domain.UserDataEntity
import com.example.eldarwallet.infrastructure.db.UserDatabase
import com.example.eldarwallet.infrastructure.repository.InAppCreditUserDataRepositoryImp

class DashboardViewModel(application: Application) : ViewModel() {

    private val database = UserDatabase.getDataBase(application)
    private val repository = InAppCreditUserDataRepositoryImp(database)
    private val getAllCreditCardsData = GetAllCreditCardsData(repository)
    private val getAllCreditCardsDataDecrypt = GetCreditCardsDataDecrypt()

    val userCreditData : LiveData<List<UserDataEntity>> = getAllCreditCardsData()

    fun decryptUserData(userDataEntities: List<UserDataEntity>) : MutableList<UserDataDecrypt> {
       return getAllCreditCardsDataDecrypt(userDataEntities)
    }
}
