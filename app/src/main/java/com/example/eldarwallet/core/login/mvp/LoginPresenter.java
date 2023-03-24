package com.example.eldarwallet.core.login.mvp;

import com.example.eldarwallet.infrastructure.repository.SharedPreferencesRepository;

public class LoginPresenter implements LoginContracts.Presenter {

    private LoginContracts.View view;
    private LoginContracts.Model model;

    public LoginPresenter(LoginContracts.View view) {
        this.view = view;
        model = new LoginModel(this);
    }

    @Override
    public void validateCredentials(String userName, String password, SharedPreferencesRepository repository) {
        if (view != null) {
            view.showProgressBar();
            model.login(userName, password, repository);
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
            view.navigateToDashboard();
            view.hideProgressBar();
        }
    }
}
