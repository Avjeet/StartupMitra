package com.teamshunya.silencio.Activities.ShowActivity.Fragments.FeedbackActivities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.SmileRating;
import com.teamshunya.silencio.Classes.StoreSession;
import com.teamshunya.silencio.R;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner_airport;
    private FloatingActionButton Submitbutton;
    private int radioID;
    private String item;
    String finalRadioValue;
    private EditText name, number, email_id, age, flight_no;
    private String nameValue, numberValue, emailidValue, flight_noValue, ageValue;
    private RadioGroup radiogrup;
    private SmileRating groundTransport, parkk, bagge, courtsyy, waittimee, courtseyInsep, easee, eatt, airportStafff, satisfa, shop, clean, avall;
    int groundRate, parkkrate, bagrate, courtsyrate, waitrate, cortsyInseprate, easerate, eatrate, airportstaffrate, satisrate, shorate, cleanrate, avalrate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        defaultValues();
        bindView();
        //Log.d("store : ", StoreSession.getInstance().readPreferencesString("PNR", ""));

        spinner_airport.setOnItemSelectedListener(this);
        List<String> list = new ArrayList<String>();
        list.add("Airport Name");
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
        groundTransport.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                groundRate = smiley + 1;
            }
        });
        parkk.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                parkkrate = smiley + 1;
            }
        });
        bagge.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                bagrate = smiley + 1;
            }
        });
        courtsyy.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                courtsyrate = smiley + 1;
            }
        });
        waittimee.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                waitrate = smiley + 1;
            }
        });
        courtseyInsep.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                cortsyInseprate = smiley + 1;
            }
        });
        easee.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                easerate = smiley + 1;
            }
        });
        eatt.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                eatrate = smiley + 1;
            }
        });
        airportStafff.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                airportstaffrate = smiley + 1;
            }
        });
        satisfa.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                satisrate = smiley + 1;
            }
        });
        shop.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                shorate = smiley + 1;
            }
        });
        clean.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                cleanrate = smiley + 1;
            }
        });
        avall.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley, boolean reselected) {
                avalrate = smiley + 1;
            }
        });


        Submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailidValue = String.valueOf(email_id.getText());
                nameValue = String.valueOf(name.getText());
                numberValue = String.valueOf(number.getText());
                ageValue = String.valueOf(age.getText());
                flight_noValue = String.valueOf(flight_no.getText());
                radioID = radiogrup.getCheckedRadioButtonId();

                switch (radioID) {
                    case R.id.radioButton1:
                        finalRadioValue = "1 to 7";
                        break;
                    case R.id.radioButton2:
                        finalRadioValue = "7+";
                        break;
                }

                DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Feedback").child(StoreSession.getInstance().readPreferencesString("PNR", "")).child("ACTUAL FEEDBACK");
                database.child("emailID").setValue(emailidValue);
                database.child("NAME").setValue(nameValue);
                database.child("Age").setValue(ageValue);
                database.child("Number").setValue(numberValue);
                database.child("FlightNo").setValue(flight_noValue);
                database.child("RadioValue").setValue(finalRadioValue);
                database.child("AirportName").setValue(item);

                database.child("ground").setValue(groundRate);
                database.child("park").setValue(parkkrate);
                database.child("bag").setValue(bagrate);
                database.child("courtsy").setValue(courtsyrate);
                database.child("waittime").setValue(waitrate);
                database.child("courtsyINSEP").setValue(cortsyInseprate);
                database.child("ease").setValue(easerate);
                database.child("eat").setValue(eatrate);
                database.child("airport staff").setValue(airportstaffrate);
                database.child("satisfaction").setValue(satisrate);
                database.child("shop").setValue(shorate);
                database.child("clean").setValue(cleanrate);
                database.child("avail").setValue(avalrate);
            }
        });
    }

    private void defaultValues() {
        groundRate =
                parkkrate =
                        bagrate =
                                courtsyrate =
                                        waitrate =
                                                cortsyInseprate =
                                                        easerate =
                                                                eatrate =
                                                                        airportstaffrate =
                                                                                satisrate =
                                                                                        shorate =
                                                                                                cleanrate =
                                                                                                        avalrate =
                                                                                                                3;
    }

    private void bindView() {
        spinner_airport = (Spinner) findViewById(R.id.spinner_airport_name);
        Submitbutton = (FloatingActionButton) findViewById(R.id.submt);
        name = (EditText) findViewById(R.id.input_name);
        number = (EditText) findViewById(R.id.input_number);
        email_id = (EditText) findViewById(R.id.input_email);
        age = (EditText) findViewById(R.id.input_age);
        flight_no = (EditText) findViewById(R.id.input_flightNumber);
        radiogrup = (RadioGroup) findViewById(R.id.radioGroup);
        groundTransport = (SmileRating) findViewById(R.id.ground);
        parkk = (SmileRating) findViewById(R.id.park);
        bagge = (SmileRating) findViewById(R.id.bag);
        courtsyy = (SmileRating) findViewById(R.id.courtesy);
        waittimee = (SmileRating) findViewById(R.id.waittime);
        courtseyInsep = (SmileRating) findViewById(R.id.courtesyInspectionandSecurit);
        easee = (SmileRating) findViewById(R.id.ease);
        eatt = (SmileRating) findViewById(R.id.eat);
        airportStafff = (SmileRating) findViewById(R.id.airportStaff);
        satisfa = (SmileRating) findViewById(R.id.satis);
        shop = (SmileRating) findViewById(R.id.shop);
        clean = (SmileRating) findViewById(R.id.clean);
        avall = (SmileRating) findViewById(R.id.aval);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }
}
