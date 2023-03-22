package com.example.eldarwallet.core.login.mvp.model;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

public class LoginInteractorImpl implements LoginInteractor{

    @Override
    public void login(String userName, String password, OnLoginResultListener listener) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (checkIsEmpty(userName)) {
                listener.userNameError();
                return;
            }

            if (checkIsEmpty(password)) {
                listener.passWordError();
                return;
            }

            listener.onSucces();

        }, 1000);
    }

    private Boolean checkIsEmpty(String string) {
        return TextUtils.isEmpty(string);
    }
}
