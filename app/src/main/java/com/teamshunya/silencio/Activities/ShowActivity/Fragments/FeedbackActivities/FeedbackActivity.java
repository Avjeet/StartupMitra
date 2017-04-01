package com.teamshunya.silencio.Activities.ShowActivity.Fragments.FeedbackActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.teamshunya.silencio.R;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner_airport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        spinner_airport = (Spinner) findViewById(R.id.spinner_airport_name);
        spinner_airport.setOnItemSelectedListener(this);
        List<String> list = new ArrayList<String>();
        list.add("Allahabad");
        list.add("New Delhi");
        list.add("Mumbai");
        list.add("Bengaluru");
        list.add("Chennai");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_airport.setAdapter(dataAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
