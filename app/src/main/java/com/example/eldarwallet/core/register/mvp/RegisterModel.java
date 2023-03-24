package com.example.eldarwallet.core.register.mvp;

import android.text.TextUtils;

import com.example.eldarwallet.infrastructure.repository.SharedPreferencesRepository;

public class RegisterModel implements RegisterContracts.Model{

    private RegisterContracts.Presenter presenter;

    public RegisterModel(RegisterContracts.Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void register(String userName, String password, String name, String lastName,SharedPreferencesRepository repository) {
        if (checkIsEmpty(userName) || checkOnlyLetters(userName)) {
            presenter.userNameError();
            return;
        }

        if (checkIsEmpty(password)) {
            presenter.passwordError();
            return;
        }

        repository.setPreferences(userName, password, name, lastName);
        presenter.onSuccess();
    }

    private Boolean checkIsEmpty(String string) {
        return TextUtils.isEmpty(string);
    }
    private Boolean checkOnlyLetters(String string) { return !string.matches("[a-zA-Z ]+"); }
}
