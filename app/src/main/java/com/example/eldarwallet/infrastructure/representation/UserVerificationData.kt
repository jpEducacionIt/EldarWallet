package com.example.eldarwallet.infrastructure.representation

import com.google.gson.annotations.SerializedName

data class UserVerificationData (
    @SerializedName("name") val name : String,
    @SerializedName("surname") val surname : String,
    @SerializedName("card_data") val cardData : List<UserCardData>
    )