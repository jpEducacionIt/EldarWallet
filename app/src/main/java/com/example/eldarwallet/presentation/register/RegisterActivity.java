package com.example.eldarwallet.presentation.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.eldarwallet.core.register.mvp.model.RegisterInteractorImpl;
import com.example.eldarwallet.core.register.mvp.presenter.RegisterPresenter;
import com.example.eldarwallet.core.register.mvp.presenter.RegisterPresenterImpl;
import com.example.eldarwallet.core.register.mvp.view.RegisterView;
import com.example.eldarwallet.databinding.ActivityRegisterBinding;
import com.example.eldarwallet.presentation.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    private ActivityRegisterBinding binding;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        registerPresenter = new RegisterPresenterImpl(this, new RegisterInteractorImpl());

        binding.buttonRegister.setOnClickListener(view1 -> {
            String userName = String.valueOf(binding.editTextRegisterName.getText());
            String password = String.valueOf(binding.editTextRegisterPassword.getText());
            registerPresenter.addCredentials(userName, password);
        });
    }

    @Override
    public void showProgressBar() {
        binding.registerProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.registerProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setUserNameError() {
        binding.editTextRegisterName.setError("Debe ser el nombre que figura en su tarjeta, sin numeros ni simbolos");
    }

    @Override
    public void setPasswordError() {
        binding.editTextRegisterPassword.setError("Password Incorrecto");
    }

    @Override
    public void saveCredentials(String userName, String password) {
        SharedPreferences preferences = getSharedPreferences("login preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_name", userName);
        editor.putString("user_password" , password);
        editor.apply();

        registerPresenter.credentialsSaved();
    }

    @Override
    public void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    protected void onDestroy() {
        registerPresenter.onDestroyView();
        super.onDestroy();
        binding = null;
    }
}