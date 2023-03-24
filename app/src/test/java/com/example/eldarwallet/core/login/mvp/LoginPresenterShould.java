package com.example.eldarwallet.core.login.mvp;


import com.example.eldarwallet.infrastructure.repository.SharedPreferencesRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterShould {

    @Mock
    private LoginContracts.View view;

    @Mock
    private LoginPresenter presenter;

    @Mock
    private SharedPreferencesRepository repository;

    @Before
    public void setup() throws Exception {
        presenter = new LoginPresenter(view);
    }

    @Test
    public void successWithValidNameAndPassword() throws Exception {
        givenARepository("juan", "1234");

        whenValidateCredentials("juan", "1234");

        thenNavigateToDashboard();
    }

    @Test
    public void unSuccessfulWithInvalidName() throws Exception {
        givenARepository("pedro", "1234");

        whenValidateCredentials("juan", "1234");

        thenShowUserNameError();
    }

    @Test
    public void unSuccessWithInvalidPassword() throws Exception {
        givenARepository("juan", "5678");

        whenValidateCredentials("juan", "1234");

        thenShowPasswordError();
    }

    @Test
    public void unSuccessWithEmptyUserName() throws Exception {
        whenValidateCredentials("", "1234");

        thenShowUserNameError();
    }

    @Test
    public void unSuccessWithEmptyPassword() throws Exception {
        givenARepository("juan", "5678");

        whenValidateCredentials("juan", "");

        thenShowPasswordError();
    }

    private void givenARepository(String name, String password) {
        Mockito.when(repository.getSharedUserName()).thenReturn(name);
        Mockito.when(repository.getSharedPassword()).thenReturn(password);
    }

    private void whenValidateCredentials(String name, String password) {
        presenter.validateCredentials(name, password, repository);
    }

    private void thenNavigateToDashboard() {
        Mockito.verify(view).navigateToDashboard();
    }

    private void thenShowUserNameError() {
        Mockito.verify(view).setUserNameError();
    }

    private void thenShowPasswordError() {
        Mockito.verify(view).setPasswordError();
    }
}