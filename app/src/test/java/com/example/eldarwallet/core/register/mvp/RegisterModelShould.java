package com.example.eldarwallet.core.register.mvp;

import com.example.eldarwallet.infrastructure.repository.SharedPreferencesRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RegisterModelShould {

    @Mock
    private RegisterModel model;

    @Mock
    private RegisterContracts.Presenter presenter;

    @Mock
    private SharedPreferencesRepository repository;

    @Before
    public void setup() throws Exception {
        model = new RegisterModel(presenter);
    }

    @Test
    public void successWithValidNameAndPassword() throws Exception {
        whenValidateData("juan", "1234");

        thenLoginIsValid();
    }

    @Test
    public void saveUserNameAndPasswordInRepository() throws Exception {
        whenValidateData("juan", "1234");

        thenSavedInRepository("juan", "1234");
    }

    @Test
    public void showErrorInvalidNameWithNumbers() throws Exception {
        whenValidateData("juan1234", "1234");

        thenLoginHasInvalidName();
    }

    @Test
    public void showErrorInvalidNameWithSpecialChar() throws Exception {
        whenValidateData("juan?=", "1234");

        thenLoginHasInvalidName();
    }

    @Test
    public void showErrorWithEmptyUserName() throws Exception {
        whenValidateData("", "1234");

        thenLoginHasInvalidName();
    }

    @Test
    public void showErrorWithEmptyPassword() throws Exception {
        whenValidateData("juan", "");

        thenLoginHasInvalidPassword();
    }

    private void whenValidateData(String userName, String password) {
        model.register(userName, password, repository);
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

    private void thenSavedInRepository(String userName, String password) {
        Mockito.verify(repository).setPreferences(userName, password);
    }
}