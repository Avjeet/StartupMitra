package com.teamshunya.silencio.Activities.ShowActivity.Fragments.FeedbackActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.teamshunya.silencio.Classes.StoreSession;
import com.teamshunya.silencio.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ComplainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner_airport, spinner_complain;
    private FloatingActionButton SubmitbuttonComplains;
    private int radioID1, radioID2;
    private ImageButton mSelectImage;
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;

    private String item1, item2;
    String finalRadioValue1, finalRadioValue2;
    private EditText name, number, email_id, age, flight_no;
    private String nameValue, numberValue, emailidValue, flight_noValue, ageValue;
    private RadioGroup radiogrup1, radiogrup2;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;
    private ProgressDialog mprogressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        bindViews();

        imageUri = null;
        mprogressbar = new ProgressDialog(this);
        spinner_airport.setOnItemSelectedListener(this);
        List<String> list = new ArrayList<String>();
        list.add(getResources().getString(R.string.airpot));
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
/////////////////////////////////////////////////////////


        spinner_complain.setOnItemSelectedListener(this);
        List<String> list1 = new ArrayList<String>();
        list1.add("Complain Type");
        list1.add("Airline");
        list1.add("AAI Services");
        list1.add("Contractor/Vendor Services");
        list1.add("Immeggration/Customs");
        list1.add("Security");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list1);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_complain.setAdapter(dataAdapter1);


        mStorageRef = FirebaseStorage.getInstance().getReference();

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photo));
                imageUri = Uri.fromFile(photo);
                startActivityForResult(intent, TAKE_PICTURE);
            }
        });


        SubmitbuttonComplains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailidValue = String.valueOf(email_id.getText());
                nameValue = String.valueOf(name.getText());
                numberValue = String.valueOf(number.getText());
                ageValue = String.valueOf(age.getText());
                flight_noValue = String.valueOf(flight_no.getText());
                radioID1 = radiogrup1.getCheckedRadioButtonId();

                switch (radioID1) {
                    case R.id.radioButtoni:
                        finalRadioValue1 = "INDIAN";
                        break;
                    case R.id.radioButtonf:
                        finalRadioValue2 = "FOREIGN";
                        break;
                }

                radioID2 = radiogrup2.getCheckedRadioButtonId();

                switch (radioID2) {
                    case R.id.radioButton1to7:
                        finalRadioValue2 = "1 to 7";
                        break;
                    case R.id.radioButton7:
                        finalRadioValue2 = "7+";
                        break;
                }

//                Log.d("asdasd", String.valueOf(imageUri));
//                Log.d("VALUES : ", item1 + item2 +
//                        emailidValue + nameValue + numberValue + ageValue +
//                        flight_noValue + finalRadioValue1 + finalRadioValue2);

                Uri file = Uri.fromFile(new File(String.valueOf(imageUri)));
                StorageReference riversRef = mStorageRef.child(StoreSession.getInstance().readPreferencesString("PNR", "")).child("IMAGES");

                riversRef.putFile(file)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                                // ...
                                Toast.makeText(getApplicationContext(), "Unabel to post Please TRY AGAIN!!!!",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

//
//                DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Feedback").child(StoreSession.getInstance().readPreferencesString("PNR", "")).child("COMPLAINS");
//                database.child("emailID").setValue(emailidValue);
//                database.child("NAME").setValue(nameValue);
//                database.child("Age").setValue(ageValue);
//                database.child("Number").setValue(numberValue);
//                database.child("FlightNo").setValue(flight_noValue);
//                database.child("NATIONALITY").setValue(finalRadioValue1);
//                database.child("No of visits in past twelve months").setValue(finalRadioValue2);
//                database.child("AirportName").setValue(item1);
//                database.child("Complain type").setValue(item2);
            }
        });


    }

    private void bindViews() {
        name = (EditText) findViewById(R.id.input_name);
        number = (EditText) findViewById(R.id.input_number);
        email_id = (EditText) findViewById(R.id.input_email);
        age = (EditText) findViewById(R.id.input_age);
        flight_no = (EditText) findViewById(R.id.input_flightNumber);
        radiogrup1 = (RadioGroup) findViewById(R.id.radioGroupn);
        radiogrup2 = (RadioGroup) findViewById(R.id.radioGroupnf);
        mSelectImage = (ImageButton) findViewById(R.id.imageButton2);
        spinner_airport = (Spinner) findViewById(R.id.spinner_airport_name);
        spinner_complain = (Spinner) findViewById(R.id.spinner_complain_type);
        SubmitbuttonComplains = (FloatingActionButton) findViewById(R.id.doneComplain);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //TODO: switch case to be implemeneted
        item1 = parent.getItemAtPosition(position).toString();
        item2 = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
