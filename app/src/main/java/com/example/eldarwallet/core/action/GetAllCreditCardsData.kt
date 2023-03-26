package com.example.eldarwallet.core.action

import androidx.lifecycle.LiveData
import com.example.eldarwallet.core.domain.UserDataEntity
import com.example.eldarwallet.infrastructure.repository.InAppCreditUserDataRepositoryImp

class GetAllCreditCardsData(
    private val repository: InAppCreditUserDataRepositoryImp
) {
    operator fun invoke(): LiveData<List<UserDataEntity>>   {
        return repository.findAll()
    }
}