package com.example.eldarwallet.presentation.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.eldarwallet.core.register.mvp.RegisterContracts;
import com.example.eldarwallet.core.register.mvp.RegisterPresenter;
import com.example.eldarwallet.databinding.ActivityRegisterBinding;
import com.example.eldarwallet.infrastructure.repository.SharedPreferencesRepository;
import com.example.eldarwallet.presentation.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterContracts.View {

    private ActivityRegisterBinding binding;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        registerPresenter = new RegisterPresenter(this);

        binding.buttonRegister.setOnClickListener(view1 -> {
            SharedPreferences preferences = getSharedPreferences("login preferences", MODE_PRIVATE);
            SharedPreferencesRepository repository = new SharedPreferencesRepository(preferences);

            String userName = String.valueOf(binding.editTextRegisterUserName.getText());
            String password = String.valueOf(binding.editTextRegisterPassword.getText());

            registerPresenter.addCredentials(userName, password, repository);
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
        binding.editTextRegisterUserName.setError("El userName no puede incluir numeros ni simbolos");
    }

    @Override
    public void setPasswordError() {
        binding.editTextRegisterPassword.setError("Password Incorrecto");
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