package com.example.eldarwallet.infrastructure.repository

import com.example.eldarwallet.core.repository.UserDataRepository
import com.example.eldarwallet.infrastructure.representation.UserCardsData

class InJsonUserDataRepositoryImp : UserDataRepository {

    override fun find(): List<UserCardsData> {
       return listOf()
    }
}