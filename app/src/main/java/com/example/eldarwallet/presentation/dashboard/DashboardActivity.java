package com.example.eldarwallet.presentation.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.eldarwallet.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}