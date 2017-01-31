package com.daepstein.marvel.marvelcharacters.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.daepstein.marvel.marvelcharacters.login.Login;
import com.daepstein.marvel.marvelcharacters.login.LoginPair;

import java.util.Map;

/**
 * Created by daepstein on 1/30/2017.
 */

public class SharedPrefs {

    Context context;

    public SharedPrefs (Context c)
    {
        context = c;
    }

    private boolean addLogin(LoginPair keys)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(keys.getPublicKey(), keys.getPrivateKey());
        return editor.commit();

    }

    private  void removeLogin ()
    {
        SharedPreferences mySPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mySPrefs.edit();
        editor.clear().apply();
    }

    public  LoginPair getKeys ()
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        Map<String, ?> logins = sharedPref.getAll();
        for (Map.Entry<String, ?> entry : logins.entrySet())
            return (new LoginPair(entry.getKey(), entry.getValue().toString()));
        return null;
    }

    public void updateKeys()
    {
        removeLogin();
        addLogin(Login.getInstance().getKeys());
    }

    public boolean isEmpty()
    {
        return (PreferenceManager.getDefaultSharedPreferences(context).getAll().isEmpty());
    }
}
