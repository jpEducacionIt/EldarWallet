package com.example.eldarwallet.infrastructure.representation

import com.google.gson.annotations.SerializedName

data class UserCardsData (
    val id : Int,
    @SerializedName("type_card") val typeCard : String,
    val name : String,
    val surname : String,
    val number : String,
    val cvv : String,
    val expired : String
    )