package com.example.eldarwallet.core.repository

import com.example.eldarwallet.infrastructure.representation.UserVerificationData

internal interface InJsonCreditUserDataRepository {
    fun find(): UserVerificationData
}