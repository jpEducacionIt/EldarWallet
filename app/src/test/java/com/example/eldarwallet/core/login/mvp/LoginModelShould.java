package com.example.eldarwallet.core.login.mvp;

import com.example.eldarwallet.infrastructure.repository.SharedPreferencesRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginModelShould {

    @Mock
    private LoginModel model;
    @Mock
    private LoginContracts.Presenter presenter;
    @Mock
    private SharedPreferencesRepository repository;

    @Before
    public void setup() throws Exception {
        model = new LoginModel(presenter);
    }

    @Test
    public void successWithValidNameAndPassword() throws Exception {
        givenARepository("juan", "1234");

        whenValidateData("juan", "1234");

        thenLoginIsValid();
    }

    @Test
    public void unSuccessfulWithInvalidName() throws Exception {
        givenARepository("pedro", "1234");

        whenValidateData("juan", "1234");

        thenLoginHasInvalidName();
    }

    @Test
    public void unSuccessWithInvalidPassword() throws Exception {
        givenARepository("juan", "5678");

        whenValidateData("juan", "1234");

        thenLoginHasInvalidPassword();
    }

    @Test
    public void unSuccessWithEmptyUserName() throws Exception {
        whenValidateData("", "1234");

        thenLoginHasInvalidName();
    }

    @Test
    public void unSuccessWithEmptyPassword() throws Exception {
        givenARepository("juan", "5678");

        whenValidateData("juan", "");

        thenLoginHasInvalidPassword();
    }

    private void givenARepository(String name, String password) {
        Mockito.when(repository.getSharedUserName()).thenReturn(name);
        Mockito.when(repository.getSharedPassword()).thenReturn(password);
    }

    private void whenValidateData(String name, String password) {
        model.login(name, password, repository);
    }

    private void thenLoginIsValid() {
        Mockito.verify(presenter).onSuccess();
    }

    private void thenLoginHasInvalidName() {
        Mockito.verify(presenter).userNameError();
    }

    private void thenLoginHasInvalidPassword() {
        Mockito.verify(presenter).passwordError();
    }
}