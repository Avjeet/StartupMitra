package com.teamshunya.silencio.Activities.ShowActivity.Fragments.FeedbackActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.teamshunya.silencio.R;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }
}
