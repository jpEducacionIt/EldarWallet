package com.example.eldarwallet.core.register.mvp.view;

public interface RegisterView {

    void showProgressBar();
    void hideProgressBar();
    void setUserNameError();
    void setPasswordError();
    void saveCredentials(String userName, String password);

    void navigateToLogin();
}
