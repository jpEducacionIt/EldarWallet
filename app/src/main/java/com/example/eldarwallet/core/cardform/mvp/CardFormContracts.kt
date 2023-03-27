package com.example.eldarwallet.core.cardform.mvp

interface CardFormContracts {
    interface View {
        fun setCardNumberError()
        fun setNameError()
        fun setSurnameError()
        fun setExpiryError()
        fun setCvvError()
        fun saveUserCreditData( userName: String,
                                surname: String,
                                cardNumber: String,
                                expiry: String,
                                cvv: String)
    }

    interface Presenter {
        fun validateData(
            userName: String,
            surname: String,
            cardNumber: String,
            expiry: String,
            cvv: String
        )

        fun nameError()
        fun surNameError()
        fun cardNumberError()
        fun expiryError()
        fun cvvError()
        fun onSuccess( userName: String,
                       surname: String,
                       cardNumber: String,
                       expiry: String,
                       cvv: String)
    }

    interface Model {
        fun validateDataModel(
            userName: String,
            surname: String,
            cardNumber: String,
            expiry: String,
            cvv: String
        )
    }
}