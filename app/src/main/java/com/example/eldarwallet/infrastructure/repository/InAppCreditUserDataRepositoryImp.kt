package com.example.eldarwallet.infrastructure.repository

import androidx.lifecycle.LiveData
import com.example.eldarwallet.core.domain.UserDataEntity
import com.example.eldarwallet.core.repository.InAppCreditUserDataRepository
import com.example.eldarwallet.infrastructure.db.UserDatabase

class InAppCreditUserDataRepositoryImp(
    private val database: UserDatabase) : InAppCreditUserDataRepository {
    override suspend fun insert(userDataEntity: UserDataEntity) {
        database.getUserDataDao().insert(userDataEntity)
    }

    override fun findAll(): LiveData<List<UserDataEntity>> {
        return database.getUserDataDao().getAllCardData()
    }
}