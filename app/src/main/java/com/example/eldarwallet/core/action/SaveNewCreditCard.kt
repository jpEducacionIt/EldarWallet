package com.example.eldarwallet.core.action

import com.example.eldarwallet.core.domain.UserDataEntity
import com.example.eldarwallet.infrastructure.service.AESEncryption
import com.example.eldarwallet.infrastructure.repository.InAppCreditUserDataRepositoryImp

class SaveNewCreditCard(
    private val roomUserDataRepository: InAppCreditUserDataRepositoryImp
) {
    suspend operator fun invoke(actionData: ActionData) {
        roomUserDataRepository.insert(actionData.toUserDataEntity())
    }
    data class ActionData(
        val id : Int,
        val surname: String,
        val name: String,
        val number: String,
        val expiry: String,
        val cvv: String
    )

    private fun ActionData.toUserDataEntity() = UserDataEntity(
        id = 0,
        typeCard = number.first().toString(),
        name = AESEncryption.encrypt(name),
        surname = AESEncryption.encrypt(surname),
        number = AESEncryption.encrypt(number),
        cvv = AESEncryption.encrypt(cvv),
        expiry = AESEncryption.encrypt(expiry)
    )
}