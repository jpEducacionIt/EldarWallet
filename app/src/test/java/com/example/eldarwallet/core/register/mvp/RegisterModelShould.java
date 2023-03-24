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
        whenValidateData("juan", "1234", "", "");

        thenLoginIsValid();
    }

    @Test
    public void saveUserNameAndPasswordInRepository() throws Exception {
        whenValidateData("juan", "1234", "pablo", "herrero");

        thenSavedInRepository("juan", "1234", "pablo", "herrero");
    }

    @Test
    public void unSuccessfulInvalidNameWithNumbers() throws Exception {
        whenValidateData("juan1234", "1234", "", "");

        thenLoginHasInvalidName();
    }

    @Test
    public void unSuccessfulInvalidNameWithSpecialChar() throws Exception {
        whenValidateData("juan?=", "1234", "", "");

        thenLoginHasInvalidName();
    }

    @Test
    public void unSuccessWithEmptyUserName() throws Exception {
        whenValidateData("", "1234", "", "");

        thenLoginHasInvalidName();
    }

    @Test
    public void unSuccessWithEmptyPassword() throws Exception {
        whenValidateData("juan", "", "", "");

        thenLoginHasInvalidPassword();
    }

    private void whenValidateData(String userName, String password, String name, String lastName) {
        model.register(userName, password, name, lastName, repository);
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

    private void thenSavedInRepository(String userName, String password, String name, String lastName) {
        Mockito.verify(repository).setPreferences(userName, password,  "pablo", "herrero");
    }
}