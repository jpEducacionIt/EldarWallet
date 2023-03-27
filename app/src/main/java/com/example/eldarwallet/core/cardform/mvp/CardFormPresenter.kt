package com.example.eldarwallet.core.cardform.mvp

class CardFormPresenter(
    private val view: CardFormContracts.View
): CardFormContracts.Presenter {

    val model = CardFormModel(this)

    override fun validateData(
        userName: String,
        surname: String,
        cardNumber: String,
        expiry: String,
        cvv: String
    ) {
        model.validateDataModel(userName, surname, cardNumber, expiry, cvv)
    }

    override fun nameError() {
        view.setNameError()
    }

    override fun surNameError() {
        view.setSurnameError()
    }

    override fun cardNumberError() {
        view.setCardNumberError()
    }

    override fun expiryError() {
        view.setExpiryError()
    }

    override fun cvvError() {
        view.setCvvError()
    }

    override fun onSuccess(
        userName: String,
        surname: String,
        cardNumber: String,
        expiry: String,
        cvv: String
    ) {
        view.saveUserCreditData(userName, surname, cardNumber, expiry, cvv)
    }
}