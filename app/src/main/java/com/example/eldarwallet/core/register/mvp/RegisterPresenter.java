package com.example.eldarwallet.core.register.mvp;

import com.example.eldarwallet.infrastructure.repository.SharedPreferencesRepository;

public class RegisterPresenter implements RegisterContracts.Presenter{

    private RegisterContracts.View view;

    private RegisterContracts.Model model;

    public RegisterPresenter(RegisterContracts.View view) {
        this.view = view;
        this.model = new RegisterModel(this);
    }

    @Override
    public void addCredentials(String userName, String password, String name, String lastName,  SharedPreferencesRepository repository) {
        if (view != null) {
            view.showProgressBar();
            model.register(userName, password, name, lastName, repository);
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void userNameError() {
        if (view != null) {
            view.setUserNameError();
            view.hideProgressBar();
        }
    }

    @Override
    public void passwordError() {
        if (view != null) {
            view.setPasswordError();
            view.hideProgressBar();
        }
    }


    @Override
    public void onSuccess() {
        if (view != null) {
            view.navigateToLogin();
            view.hideProgressBar();
        }
    }
}
