package com.example.eldarwallet.core.register.mvp;

import com.example.eldarwallet.infrastructure.repository.SharedPreferencesRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RegisterPresenterShould {

    @Mock
    private RegisterPresenter presenter;

    @Mock
    private RegisterContracts.View view;

    @Mock
    private SharedPreferencesRepository repository;

    @Before
    public void setup() throws Exception {
        presenter = new RegisterPresenter(view);
    }

    @Test
    public void successWithValidNameAndPassword() throws Exception {
        whenValidateData("juan", "1234", "", "");

        thenLoginIsValid();
    }

    @Test
    public void showErrorInvalidNameWithNumbers() throws Exception {
        whenValidateData("juan1234", "1234", "", "");

        thenLoginHasInvalidName();
    }

    @Test
    public void showErrorInvalidNameWithSpecialChar() throws Exception {
        whenValidateData("juan?=", "1234", "", "");

        thenLoginHasInvalidName();
    }

    @Test
    public void showErrorWithEmptyUserName() throws Exception {
        whenValidateData("", "1234", "", "");

        thenLoginHasInvalidName();
    }

    @Test
    public void showErrorWithEmptyPassword() throws Exception {
        whenValidateData("juan", "", "", "");

        thenLoginHasInvalidPassword();
    }

    private void whenValidateData(String userName, String password, String name, String lastName ) {
        presenter.addCredentials(userName, password, repository);
    }

    private void thenLoginIsValid() {
        Mockito.verify(view).navigateToLogin();
    }

    private void thenLoginHasInvalidName() {
        Mockito.verify(view).setUserNameError();
    }

    private void thenLoginHasInvalidPassword() {
        Mockito.verify(view).setPasswordError();
    }

}