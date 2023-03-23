package com.example.eldarwallet.core.login.mvp.presenter;

import com.example.eldarwallet.core.login.mvp.model.LoginInteractor;
import com.example.eldarwallet.core.login.mvp.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginResultListener {

    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view, LoginInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void validateCredentials(String userName, String password) {
        if (view != null) {
            view.showProgressBar();
            interactor.login(userName, password, this);
        }
    }

    @Override
    public void userNameError() {
        if (view != null) {
            view.setUserNameError();
            view.hideProgressBar();
        }
    }

    @Override
    public void passWordError() {
        if (view != null) {
            view.setPasswordError();
            view.hideProgressBar();
        }
    }

    @Override
    public void onSucces() {
        if (view != null) {
            view.navigateToDashboard();
            view.hideProgressBar();
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }
}
