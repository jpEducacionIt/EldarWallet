package com.example.eldarwallet.core.repository

import androidx.lifecycle.LiveData
import com.example.eldarwallet.core.domain.UserDataEntity

internal interface InAppCreditUserDataRepository {
    suspend fun insert(userDataEntity: UserDataEntity)
    fun findAll(): LiveData<List<UserDataEntity>>
}