package com.example.eldarwallet.core.repository

import com.example.eldarwallet.infrastructure.representation.UserVerificationData

internal interface UserDataRepository {
    fun find() : UserVerificationData
}