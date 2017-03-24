package com.teamshunya.silencio;

import android.app.Application;
import android.content.Context;

/**
 * Created by himanshusingh on 24/03/17.
 */

public class MyApplication extends Application {

    public static Context getContext() {
        return context;
    }

    private static Context context ;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
