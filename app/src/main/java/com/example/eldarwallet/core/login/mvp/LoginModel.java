package com.example.eldarwallet.core.login.mvp;

import android.text.TextUtils;

import com.example.eldarwallet.infrastructure.repository.SharedPreferencesRepository;

public class LoginModel implements LoginContracts.Model {

    private LoginContracts.Presenter presenter;

    public LoginModel(LoginContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void login(String userName, String password, SharedPreferencesRepository repository) {

        if (checkIsEmpty(userName) || !userName.equals(repository.getSharedUserName())) {
            presenter.userNameError();
            return;
        }

        if (checkIsEmpty(password) || !password.equals(repository.getSharedPassword())) {
            presenter.passwordError();
            return;
        }

        presenter.onSuccess();
    }

    private Boolean checkIsEmpty(String string) {
        return TextUtils.isEmpty(string);
    }
}
