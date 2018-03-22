package com.teamshunya.silencio.Classes;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.preference.PreferenceManager;

import com.teamshunya.silencio.MyApplication;

/**
 * Created by himanshusingh on 24/03/17.
 */
public class StoreSession {
    
    public static StoreSession storeSession; 
    
    public static StoreSession getInstance() {
        if(storeSession == null) {
            return  new StoreSession();
        }
        return storeSession;
    }

    public void savePreferencesString(String key, String value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public  String readPreferencesString( String key, String defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        return sp.getString(key, defaultValue);
    }
    public String deletePreferencesString(String key,String defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        sp.edit().remove(key).apply();
        return sp.getString(key, defaultValue);
    }
    public  void savePreferencesBoolean( String key, Boolean value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public  boolean readPreferencesBoolean( String key, Boolean defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        return sp.getBoolean(key, defaultValue);
    }
    public  void savePreferencesInt( String key, int value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public  int readPreferencesInt( String key, int defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        return sp.getInt(key, defaultValue);
    }


}