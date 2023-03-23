package com.example.eldarwallet.core.register.mvp.model;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

public class RegisterInteractorImpl implements RegisterInteractor {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    public void register(String userName, String password, OnLoginResultListener listener) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (checkIsEmpty(userName) || checkOnlyLetters(userName)) {
                listener.userNameError();
                return;
            }

            if (checkIsEmpty(password)) {
                listener.passwordError();
                return;
            }

            listener.onSucces(userName.toLowerCase(), password);

        }, 1000);
    }

    private Boolean checkIsEmpty(String string) {
        return TextUtils.isEmpty(string);
    }
    private Boolean checkOnlyLetters(String string) { return !string.matches("[a-zA-Z ]+"); }
}
