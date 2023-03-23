package com.example.eldarwallet.core.register.mvp.presenter;

import com.example.eldarwallet.core.register.mvp.model.RegisterInteractor;
import com.example.eldarwallet.core.register.mvp.view.RegisterView;

public class RegisterPresenterImpl implements RegisterPresenter, RegisterInteractor.OnLoginResultListener {

    private RegisterView view;
    private RegisterInteractor interactor;

    public RegisterPresenterImpl(RegisterView view, RegisterInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void addCredentials(String userName, String password) {
        if (view != null) {
            view.showProgressBar();
            interactor.register(userName, password, this);
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
    public void passwordError() {
        if (view != null) {
            view.setPasswordError();
            view.hideProgressBar();
        }
    }

    @Override
    public void onSucces(String userName, String password) {
        if (view != null) {
            view.saveCredentials(userName, password);
            view.hideProgressBar();
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void credentialsSaved() {
        view.navigateToLogin();
    }
}
