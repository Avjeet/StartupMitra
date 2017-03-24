package com.teamshunya.silencio.Classes;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by himanshusingh on 24/03/17.
 */
public class StoreSession {

    public static void savePreferencesString(Activity activity, String key, String value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String readPreferencesString(Activity activity, String key, String defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        return sp.getString(key, defaultValue);
    }
    public static void savePreferencesBoolean(Activity activity, String key, Boolean value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean readPreferencesBoolean(Activity activity, String key, Boolean defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        return sp.getBoolean(key, defaultValue);
    }
    public static void savePreferencesInt(Activity activity, String key, int value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int readPreferencesInt(Activity activity, String key, int defaultValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        return sp.getInt(key, defaultValue);
    }


}