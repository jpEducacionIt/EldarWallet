package com.example.eldarwallet.core.action

import android.os.Parcelable
import com.example.eldarwallet.core.domain.UserDataEntity
import com.example.eldarwallet.infrastructure.service.AESEncryption
import kotlinx.parcelize.Parcelize

class GetCreditCardsDataDecrypt {

    operator fun invoke(userDataEntities: List<UserDataEntity>): MutableList<UserDataDecrypt> {
        userDataEntities.map { it.toUserData() }
        return userDataEntities.map { it.toUserData() } as MutableList<UserDataDecrypt>
    }

    private fun UserDataEntity.toUserData() = UserDataDecrypt(
        id = id,
        typeCard = typeCard,
        name = AESEncryption.decrypt(name),
        surname = AESEncryption.decrypt(surname),
        number = AESEncryption.decrypt(number),
        cvv = AESEncryption.decrypt(cvv),
        expiry = AESEncryption.decrypt(expiry)
    )
}

@Parcelize
data class UserDataDecrypt(
    val id : Int,
    val typeCard: String,
    val surname: String,
    val name: String,
    val number: String,
    val expiry: String,
    val cvv: String,
    var isSelected: Boolean = false
) : Parcelable