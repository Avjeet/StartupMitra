package com.teamshunya.silencio.Activities.ShowActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.teamshunya.silencio.Activities.ShowActivity.ShakeListener.ShakeListener;
import com.teamshunya.silencio.Classes.GPSTracker;
import com.teamshunya.silencio.Classes.MyLocationListener;
import com.teamshunya.silencio.Database.db;
import com.teamshunya.silencio.Models.PhoneNumber;
import com.teamshunya.silencio.R;

import java.util.List;

import static com.karumi.dexter.Dexter.*;

public class SOS extends AppCompatActivity {

    db dbhandler;
    ProgressDialog pd;
    EditText Msg;
    GPSTracker gps;
    double latitude, longitude;
    Button ClickMe;

    private ShakeListener mShaker;


    //btnsend



    public void message(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        mShaker = new ShakeListener(this);
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
            public void onShake()
            {
                vibe.vibrate(900);
                if (dbhandler.number() == 2) {
                    String phoneNo1 = dbhandler.databaseToPhoneFirst();
                    String phoneNo2 = dbhandler.databaseToPhoneSecond();
                    Double latitude = 0.0, longitude;
                    String message = "Need Your Help. I am in danger.Please Contact me ASAP";
                    LocationManager mlocManager = null;
                    LocationListener mlocListener;
                    mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    mlocListener = new MyLocationListener();
                    mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
                    if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        latitude = MyLocationListener.latitude;
                        longitude = MyLocationListener.longitude;
                        message = message + "\n My Location is - " + latitude + "," + longitude;
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        if (latitude == 0.0) {
                            Toast.makeText(getApplicationContext(), "Currently gps has not found your location....", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "GPS is currently off...", Toast.LENGTH_LONG).show();
                    }
                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNo1, null, message, null, null);
                        //Toast.makeText(getApplicationContext(), "SMS1 sent.", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        // Toast.makeText(getApplicationContext(), "SMS1 faild, please try again.", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNo2, null, message, null, null);
                        //Toast.makeText(getApplicationContext(), "SMS2 sent.", Toast.LENGTH_LONG).show();
                        // Toast.makeText(getApplicationContext(), "You have sent this message: "+ message, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        // Toast.makeText(getApplicationContext(), "SMS2 faild, please try again.", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        });


        ClickMe = (Button) findViewById(R.id.Clickme);
        ClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SOS.this, Addnums.class));
            }
        });
        withActivity(this)
                .withPermissions(
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();

//key
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dbhandler = new db(this, null, null, 1);
        Bundle numbers = getIntent().getExtras();
        if (numbers == null) {
            return;
        }
        String number1 = numbers.getString("Number1");
        String number2 = numbers.getString("Number2");
        PhoneNumber n1 = new PhoneNumber(number1);
        PhoneNumber n2 = new PhoneNumber(number2);
        dbhandler.addnumber1(n1);
        dbhandler.addnumber2(n2);
    }
    @Override
    public void onResume()
    {
        mShaker.resume();
        super.onResume();
    }
    @Override
    public void onPause()
    {
        mShaker.pause();
        super.onPause();
    }
}
