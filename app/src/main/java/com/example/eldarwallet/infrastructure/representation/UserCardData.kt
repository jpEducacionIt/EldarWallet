package com.example.eldarwallet.infrastructure.representation

import com.google.gson.annotations.SerializedName

data class UserCardData (
    @SerializedName("id")val id : String,
    @SerializedName("type_card")val typeCard : String,
    @SerializedName("name")val name : String,
    @SerializedName("surname")val surname : String,
    @SerializedName("number")val number : String,
    @SerializedName("cvv")val cvv : String,
    @SerializedName("expired")val expired : String
    )