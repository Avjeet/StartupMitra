package com.teamshunya.silencio.Activities.ShowActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.teamshunya.silencio.Classes.CustomFontTextView;
import com.teamshunya.silencio.Classes.PrefManager;
import com.teamshunya.silencio.R;
import com.teamshunya.silencio.SplashActivity;

import java.util.Locale;

public class SetLanguage extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private Locale myLocale;
    private RadioGroup mLanguage;
    private CustomFontTextView pick;
    private RadioButton mEng, mHindi;
    private Button Continue;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_language);
        loadLocale();
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
        else {
            Continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    prefManager.setFirstTimeLaunch(false);
                    startActivity(new Intent(SetLanguage.this,ShowActivity.class));
                    finish();
                }
            });
        }



    }

    private void launchHomeScreen() {
        startActivity(new Intent(SetLanguage.this,SplashActivity.class));
    }

    private void loadLocale() {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
        addListenerOnButton();
        mLanguage.setOnCheckedChangeListener(this);

    }


    private void addListenerOnButton() {
        pick = (CustomFontTextView)findViewById(R.id.pick);
        mLanguage = (RadioGroup) findViewById(R.id.language);
        mEng = (RadioButton) findViewById(R.id.eng);
        mHindi = (RadioButton) findViewById(R.id.hindi);
        Continue = (Button) findViewById(R.id.continuee);
    }

    public void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

    }

    private void updateTextsHindi() {
        Continue.setText(getResources().getString(R.string.string));
        pick.setText(getResources().getString(R.string.pickHindi));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        String lang = "en";
        switch (checkedId) {
            case R.id.eng:
                lang = "en";
                updateTextsEnglish();
                break;
            case R.id.hindi:
                lang = "hi";
                updateTextsHindi();
                break;
            default:
                break;

        }
        changeLang(lang);

    }

    private void updateTextsEnglish() {
        Continue.setText("Continue");
        pick.setText("Pick Your Language");
    }

    @Override
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (myLocale != null) {
            newConfig.locale = myLocale;
            Locale.setDefault(myLocale);
            getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        }
    }
}
