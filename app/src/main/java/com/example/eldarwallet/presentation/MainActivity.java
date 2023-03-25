package com.example.eldarwallet.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.eldarwallet.R;
import com.example.eldarwallet.databinding.ActivityLoginBinding;
import com.example.eldarwallet.databinding.ActivityMainBinding;
import com.example.eldarwallet.presentation.login.LoginActivity;
import com.example.eldarwallet.presentation.register.RegisterActivity;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferences = getSharedPreferences("login preferences", MODE_PRIVATE);

        if (preferences.contains("user_name")) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}