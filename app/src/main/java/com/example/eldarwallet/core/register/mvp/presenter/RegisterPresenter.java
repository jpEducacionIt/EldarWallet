package com.example.eldarwallet.core.register.mvp.presenter;

import android.content.Context;

public interface RegisterPresenter {

    void addCredentials(String userName, String password);
    void onDestroyView();

    void credentialsSaved();
}
