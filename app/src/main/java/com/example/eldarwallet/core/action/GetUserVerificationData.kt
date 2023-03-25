package com.example.eldarwallet.core.action

import com.example.eldarwallet.infrastructure.repository.InJsonUserDataRepositoryImp
import com.example.eldarwallet.infrastructure.representation.UsersData

class GetUserVerificationData(
    private val repository: InJsonUserDataRepositoryImp
) {
    operator fun invoke(): UsersData {
        return repository.find()
    }
}