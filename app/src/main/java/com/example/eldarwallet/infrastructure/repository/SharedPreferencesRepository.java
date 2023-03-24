package com.example.eldarwallet.infrastructure.repository;

import android.content.SharedPreferences;

public class SharedPreferencesRepository {
    private SharedPreferences preferences;

    public SharedPreferencesRepository(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public String getSharedUserName() {
        return preferences.getString("user_name", "");
    }

    public String getSharedPassword() {
        return preferences.getString("user_password", "");
    }

    public void setPreferences(String userName, String password) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_name", userName);
        editor.putString("user_password" , password);
        editor.apply();
    }
}
