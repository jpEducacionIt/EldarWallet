package com.example.eldarwallet.core.login.mvp.presenter;

public interface LoginPresenter {

    void validateCredentials(String userName, String password, String sharedName, String sharedPassword);
    void onDestroyView();
}
