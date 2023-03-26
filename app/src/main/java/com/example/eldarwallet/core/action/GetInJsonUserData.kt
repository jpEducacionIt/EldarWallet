package com.example.eldarwallet.core.action

import com.example.eldarwallet.infrastructure.repository.InJsonCreditUserDataRepositoryImp
import com.example.eldarwallet.infrastructure.representation.UserVerificationData

class GetInJsonUserData(
    private val jsonUserDataRepository: InJsonCreditUserDataRepositoryImp
) {
    operator fun invoke(): UserVerificationData {
        return jsonUserDataRepository.find()
    }
}