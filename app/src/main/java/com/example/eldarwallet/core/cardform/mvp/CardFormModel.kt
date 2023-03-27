package com.example.eldarwallet.core.cardform.mvp

import android.text.TextUtils

class CardFormModel(private val presenter: CardFormContracts.Presenter): CardFormContracts.Model {

    override fun validateDataModel(
        userName: String,
        surname: String,
        cardNumber: String,
        expiry: String,
        cvv: String
    ) {
        if (checkIsEmpty(userName)) {
            presenter.nameError()
            return
        }

        if (checkIsEmpty(surname)) {
            presenter.surNameError()
            return
        }

        if (checkIsEmpty(cardNumber)) {
            presenter.cardNumberError()
            return
        }

        if (checkIsEmpty(expiry)) {
            presenter.expiryError()
            return
        }

        if (checkIsEmpty(cvv)) {
            presenter.cvvError()
            return
        }

        presenter.onSuccess(userName, surname, cardNumber, expiry, cvv)
    }

    private fun checkIsEmpty(string: String): Boolean = TextUtils.isEmpty(string)
}