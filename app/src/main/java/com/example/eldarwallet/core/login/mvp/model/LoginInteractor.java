package com.example.eldarwallet.core.login.mvp.model;

public interface LoginInteractor {

    interface OnLoginResultListener {

        void userNameError();
        void passWordError();
        void onSucces();
    }

    void login (String userName, String password, String sharedName, String sharedPassword, OnLoginResultListener listener);
}
