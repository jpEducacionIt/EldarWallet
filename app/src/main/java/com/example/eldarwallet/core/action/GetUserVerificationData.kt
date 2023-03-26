package com.example.eldarwallet.core.action

import com.example.eldarwallet.infrastructure.repository.InJsonUserDataRepositoryImp
import com.example.eldarwallet.infrastructure.representation.UserVerificationData

class GetUserVerificationData(
    private val repository: InJsonUserDataRepositoryImp
) {
    operator fun invoke(): UserVerificationData {
        return repository.find()
    }
}