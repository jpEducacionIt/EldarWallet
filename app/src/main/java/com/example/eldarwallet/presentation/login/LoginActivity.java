package com.example.eldarwallet.presentation.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.eldarwallet.core.login.mvp.LoginContracts;
import com.example.eldarwallet.core.login.mvp.LoginPresenter;
import com.example.eldarwallet.databinding.ActivityLoginBinding;
import com.example.eldarwallet.infrastructure.repository.SharedPreferencesRepository;

public class LoginActivity extends AppCompatActivity implements LoginContracts.View {
    private LoginPresenter loginPresenter;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        loginPresenter = new LoginPresenter(this);

        binding.buttonLogin.setOnClickListener(view1 -> {
            SharedPreferences preferences = getSharedPreferences("login preferences", MODE_PRIVATE);
            SharedPreferencesRepository repository = new SharedPreferencesRepository(preferences);

            String userName = String.valueOf(binding.editTextPersonName.getText());
            String password = String.valueOf(binding.editTextPersonPassword.getText());
            loginPresenter.validateCredentials(userName.toLowerCase(), password, repository );
        });
    }


    @Override
    public void showProgressBar() {
        binding.loginProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.loginProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setUserNameError() {
        binding.editTextPersonName.setError("Nombre de usuario incorrecto");
    }

    @Override
    public void setPasswordError() {
        binding.editTextPersonPassword.setError("Password Incorrecto");
    }

    @Override
    public void navigateToDashboard() {
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroyView();
        super.onDestroy();
        binding = null;
    }
}