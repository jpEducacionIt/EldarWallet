package com.example.eldarwallet.presentation.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eldarwallet.R;
import com.example.eldarwallet.core.login.mvp.view.LoginView;

public class LoginFragment extends Fragment implements LoginView, View.OnClickListener {

    public LoginFragment() { }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void setUserNameError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void navigateToDashboard() {

    }

    @Override
    public void onClick(View view) {

    }
}