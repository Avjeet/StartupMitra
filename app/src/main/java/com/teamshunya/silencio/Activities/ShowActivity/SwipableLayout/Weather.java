package com.teamshunya.silencio.Activities.ShowActivity.SwipableLayout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamshunya.silencio.Classes.CustomFontTextView;
import com.teamshunya.silencio.Classes.Function;
import com.teamshunya.silencio.Classes.GPSTracker;
import com.teamshunya.silencio.R;

public class Weather extends Fragment {
    CustomFontTextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;
    Typeface weatherFont;

    GPSTracker gps;
    double latitude,longitude;
    public Weather() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gps = new GPSTracker(getContext());
        if(gps.canGetLocation()){
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            weatherFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");
            Function.placeIdTask asyncTask =new Function.placeIdTask(new Function.AsyncResponse() {
                public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                    cityField.setText(weather_city);
                    updatedField.setText(weather_updatedOn);
                    detailsField.setText(weather_description);
                    currentTemperatureField.setText(weather_temperature +"C");
                    humidity_field.setText("Humidity: "+weather_humidity);
                    pressure_field.setText("Pressure: "+weather_pressure);
                    weatherIcon.setText(Html.fromHtml(weather_iconText));

                }
            });
            asyncTask.execute(String.valueOf(latitude), String.valueOf(longitude));

        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
           // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    private void bindViews(View view) {

        cityField = (CustomFontTextView)view.findViewById(R.id.city_field);
        updatedField = (CustomFontTextView)view.findViewById(R.id.updated_field);
        detailsField = (CustomFontTextView)view.findViewById(R.id.details_field);
        currentTemperatureField = (CustomFontTextView)view.findViewById(R.id.current_temperature_field);
        humidity_field = (CustomFontTextView)view.findViewById(R.id.humidity_field);
        pressure_field = (CustomFontTextView)view.findViewById(R.id.pressure_field);
        weatherIcon = (CustomFontTextView)view.findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);
    }


}