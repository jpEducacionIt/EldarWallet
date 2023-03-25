package com.example.eldarwallet.infrastructure.representation

import com.google.gson.annotations.SerializedName

data class UsersData (
    @SerializedName("data") val data : List<UserVerificationData>
    )