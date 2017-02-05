package com.teamshunya.silencio.FeedBack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.teamshunya.silencio.R;

public class Add extends AppCompatActivity {

    RatingBar ratingHospitality, ratingAmbience,ratingTransport,ratingLounge,ratingEating;
    RatingBar ratingClean, ratingBaggage,ratingSecurity;
    EditText comment,flightNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Feedback");

        ratingHospitality = (RatingBar)findViewById(R.id.ratingHospitality);

//        Toast.makeText(Add.this,
//                String.valueOf(ratingHospitality.getRating()),
//                Toast.LENGTH_SHORT).show();
    }
}
