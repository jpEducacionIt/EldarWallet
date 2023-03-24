package com.example.eldarwallet.core.repository

import com.example.eldarwallet.infrastructure.representation.UserCardsData

internal interface UserDataRepository {
    fun find() :  List<UserCardsData>
}