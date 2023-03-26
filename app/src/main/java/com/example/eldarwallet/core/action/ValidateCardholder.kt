package com.example.eldarwallet.core.action

import com.example.eldarwallet.core.domain.CardHolderResponse
import com.example.eldarwallet.infrastructure.repository.InJsonCreditUserDataRepositoryImp
import com.example.eldarwallet.infrastructure.representation.UserVerificationData

class ValidateCardholder(
    private val jsonUserDataRepository: InJsonCreditUserDataRepositoryImp
) {
    operator fun invoke(actionData: ActionData): CardHolderResponse {
        val userVerificationData = jsonUserDataRepository.find()
        return validateCardHolderData(actionData.name, actionData.surname, userVerificationData)
    }

    private fun validateCardHolderData(
        name: String,
        surname: String,
        userVerificationData: UserVerificationData
    ): CardHolderResponse {
        if (name == userVerificationData.name && (surname == userVerificationData.surname)) {
            return CardHolderResponse.SUCCESS
        }
        return CardHolderResponse.NAME_ERROR
    }

    data class ActionData(
        val surname: String,
        val name: String,
    )
}
