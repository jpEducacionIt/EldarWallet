package com.example.eldarwallet.core.register.mvp;

import com.example.eldarwallet.infrastructure.repository.SharedPreferencesRepository;

public interface RegisterContracts {
    interface View {
        void showProgressBar();
        void hideProgressBar();
        void setUserNameError();
        void setPasswordError();
        void navigateToLogin();

    }

    interface Presenter {
        void addCredentials(String userName, String password, SharedPreferencesRepository repository);
        void onDestroyView();
        void userNameError();
        void passwordError();
        void onSuccess();
    }

    interface Model {
        void register(String userName, String password, SharedPreferencesRepository repository);
    }
}
