package com.example.eldarwallet.core.repository

import com.example.eldarwallet.infrastructure.representation.UserVerificationData
import com.example.eldarwallet.infrastructure.representation.UsersData

internal interface UserDataRepository {
    fun find() : UsersData
}