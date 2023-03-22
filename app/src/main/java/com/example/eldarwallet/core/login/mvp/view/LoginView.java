package com.example.eldarwallet.core.login.mvp.view;

public interface LoginView {

    void showProgressBar();
    void hideProgressBar();
    void setUserNameError();
    void setPasswordError();
    void navigateToDashboard();
}
