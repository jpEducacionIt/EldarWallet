package com.example.eldarwallet.presentation.dashboard.fragments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.eldarwallet.core.action.GetAllCreditCardsData
import com.example.eldarwallet.core.action.SaveNewCreditCard
import com.example.eldarwallet.core.domain.UserDataEntity
import com.example.eldarwallet.infrastructure.AESEncryption
import com.example.eldarwallet.infrastructure.db.UserDatabase
import com.example.eldarwallet.infrastructure.repository.InAppCreditUserDataRepositoryImp

class DashboardViewModel(application: Application) : ViewModel() {

    private val database = UserDatabase.getDataBase(application)
    private val repository = InAppCreditUserDataRepositoryImp(database)
    private val getAllCreditCardsData = GetAllCreditCardsData(repository)

    val userCreditData : LiveData<List<UserDataEntity>> = getAllCreditCardsData()

    fun decryptUserData(userDataEntities: List<UserDataEntity>) : List<UserData> {
        val list: MutableList<UserData> = mutableListOf()
        userDataEntities.forEach {
            list.add(it.toUserData())
        }
        return list
    }

    private fun UserDataEntity.toUserData() = UserData(
        id = id,
        typeCard = typeCard,
        name = AESEncryption.decrypt(name!!),
        surname = AESEncryption.decrypt(surname!!),
        number = AESEncryption.decrypt(number!!),
        cvv = AESEncryption.decrypt(cvv!!),
        expiry = AESEncryption.decrypt(expiry!!)
    )
}

data class UserData(
    val id : Int,
    val typeCard: String,
    val surname: String?,
    val name: String?,
    val number: String?,
    val expiry: String?,
    val cvv: String?
)
