package com.example.eldarwallet.core.action

import com.example.eldarwallet.core.domain.UserDataEntity
import com.example.eldarwallet.infrastructure.AESEncryption

class GetCreditCardsDataDecrypt {

    operator fun invoke(userDataEntities: List<UserDataEntity>): List<UserDataDecrypt> {
        val list: MutableList<UserDataDecrypt> = mutableListOf()
        userDataEntities.forEach {
            list.add(it.toUserData())
        }
        return list
    }

    private fun UserDataEntity.toUserData() = UserDataDecrypt(
        id = id,
        typeCard = typeCard,
        name = AESEncryption.decrypt(name!!),
        surname = AESEncryption.decrypt(surname!!),
        number = AESEncryption.decrypt(number!!),
        cvv = AESEncryption.decrypt(cvv!!),
        expiry = AESEncryption.decrypt(expiry!!)
    )
}

data class UserDataDecrypt(
    val id : Int,
    val typeCard: String,
    val surname: String?,
    val name: String?,
    val number: String?,
    val expiry: String?,
    val cvv: String?
)