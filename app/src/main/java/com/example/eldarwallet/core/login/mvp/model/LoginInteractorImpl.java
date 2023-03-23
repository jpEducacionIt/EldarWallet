package com.example.eldarwallet.core.login.mvp.model;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

public class LoginInteractorImpl implements LoginInteractor{

    @Override
    public void login(String userName, String password, String sharedName, String sharedPassword, OnLoginResultListener listener) {

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (checkIsEmpty(userName) || validate(userName, sharedName)) {
                listener.userNameError();
                return;
            }

            if (checkIsEmpty(password) || validate(password, sharedPassword)) {
                listener.passWordError();
                return;
            }

            listener.onSucces();

        }, 1000);
    }

    private boolean validate(String firstString, String secondString) {
        return !firstString.equals(secondString);
    }

    private Boolean checkIsEmpty(String string) {
        return TextUtils.isEmpty(string);
    }
}
