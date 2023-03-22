package com.example.eldarwallet.core.login.mvp.presenter;

public interface LoginPresenter {

    void validateCredentials(String userName, String password);
    void onDestroyView();
}
