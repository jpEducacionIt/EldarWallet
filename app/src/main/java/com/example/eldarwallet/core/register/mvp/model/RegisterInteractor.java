package com.example.eldarwallet.core.register.mvp.model;

public interface RegisterInteractor {

    interface OnLoginResultListener {

        void userNameError();
        void passwordError();
        void onSucces(String userName, String password);
    }

    void register(String userName, String password, OnLoginResultListener listener);
}
