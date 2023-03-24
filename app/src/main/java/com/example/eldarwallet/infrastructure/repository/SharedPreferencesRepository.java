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

    public void setPreferences(String userName, String password, String name, String lastName) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_name", userName);
        editor.putString("user_password" , password);
        editor.putString("name", name);
        editor.putString("last_name" , lastName);
        editor.apply();
    }
}
