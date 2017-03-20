package com.teamshunya.silencio.ShowActivity.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;
import com.teamshunya.silencio.R;


public class Feedback extends android.support.v4.app.Fragment {
    SmileRating smileRating0, smileRating1, smileRating2, smileRating3, smileRating4, smileRating5, smileRating6, smileRating7, smileRating8;
    Button submitButton;
    int rate0, rate1, rate2, rate3, rate4, rate5, rate6, rate7, rate8;
    //default value will be 3 ...as it is already selected in UI
    //we need defalut values bcz onSmylieselctionlistener will not execute if user directly clicks on submit without clicking on any of the feedback
    EditText textFeedback;
    String textFeddbackToFirebase;
    String userid;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public Feedback() {

    }


    private void bindViews(View rootLayout) {
        rate0=rate1=rate2=rate3=rate4=rate5=rate6=rate7=rate8 =3;
        if (user != null) {

            userid = user.getUid();//we'll take the userid from auth and will store the feddback for that uid only

            smileRating0 = (SmileRating) rootLayout.findViewById(R.id.smile_rating0);
            smileRating1 = (SmileRating) rootLayout.findViewById(R.id.smile_rating1);
            smileRating2 = (SmileRating) rootLayout.findViewById(R.id.smile_rating2);
            smileRating3 = (SmileRating) rootLayout.findViewById(R.id.smile_rating3);
            smileRating4 = (SmileRating) rootLayout.findViewById(R.id.smile_rating4);
            smileRating5 = (SmileRating) rootLayout.findViewById(R.id.smile_rating5);
            smileRating6 = (SmileRating) rootLayout.findViewById(R.id.smile_rating6);
            smileRating7 = (SmileRating) rootLayout.findViewById(R.id.smile_rating7);
            smileRating8 = (SmileRating) rootLayout.findViewById(R.id.smile_rating8);

            submitButton = (Button) rootLayout.findViewById(R.id.submitButoon);
            textFeedback = (EditText) rootLayout.findViewById(R.id.textFeedbackFromUser);

            //0
            smileRating0.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                @Override
                public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                    // reselected is false when user selects different smiley that previously selected one
                    // true when the same smiley is selected.
                    // Except if it first time, then the value will be false.
                    // Toast.makeText(getContext(), "Thank You for Feedback !"+smiley, Toast.LENGTH_LONG).show();
                    rate0 = smiley + 1;
                }
            });
            //1
            smileRating1.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                @Override
                public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                    rate1 = smiley + 1;
                }
            });
            //2
            smileRating2.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                @Override
                public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                    rate2 = smiley + 1;
                }
            });
            //3
            smileRating3.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                @Override
                public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                    rate3 = smiley + 1;
                }
            });
            //4
            smileRating4.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                @Override
                public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                    rate4 = smiley + 1;
                }
            });
            //5
            smileRating5.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                @Override
                public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                    rate5 = smiley + 1;
                }
            });
            //6
            smileRating6.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                @Override
                public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                    rate6 = smiley + 1;
                }
            });

            //7
            smileRating2.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                @Override
                public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                    rate7 = smiley + 1;
                }
            });

            //8
            smileRating8.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                @Override
                public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                    rate8 = smiley + 1;
                }
            });

            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    textFeddbackToFirebase = textFeedback.getText().toString();
                    //Toast.makeText(getContext(), "" + userid, Toast.LENGTH_LONG).show();

                    DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Feddback").child(userid);
                    //here we can ad the condition to chek that already the feedback of that user(means userid) is exist or not nd will move accordingly

                    database.child("Airport hospitality").setValue(rate0);
                    database.child("Airport Ambience").setValue(rate1);
                    database.child("Airport cleanliness").setValue(rate2);
                    database.child("Transport").setValue(rate3);
                    database.child("Parking").setValue(rate4);
                    database.child("Lounge").setValue(rate5);
                    database.child("Eating Area").setValue(rate6);
                    database.child("Baggage Claim").setValue(rate7);
                    database.child("Navigation Ease").setValue(rate8);

                    if (textFeddbackToFirebase != null) {
                        database.child("other").setValue(textFeddbackToFirebase);
                    }
                    database.keepSynced(true);
                }
            });
        } else {
            // if userid dosent exist we'll not show the feeedback
            //dont kn how to do -vaibhav
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
    }
}