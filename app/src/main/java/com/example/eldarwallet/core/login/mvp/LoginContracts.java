package com.example.eldarwallet.core.login.mvp;


import com.example.eldarwallet.infrastructure.repository.SharedPreferencesRepository;

public interface LoginContracts {
    interface View {
        void showProgressBar();
        void hideProgressBar();
        void setUserNameError();
        void setPasswordError();
        void navigateToDashboard();
    }

    interface Presenter {
        void validateCredentials
                (String userName,
                 String password,
                 SharedPreferencesRepository repository);
        void onDestroyView();

        void userNameError();
        void passwordError();
        void onSuccess();
    }

    interface Model {
        void login (String userName,
                    String password,
                    SharedPreferencesRepository repository);
    }
}
