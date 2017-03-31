package com.teamshunya.silencio.Activities.ShowActivity.Fragments;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamshunya.silencio.Activities.ShowActivity.Fragments.FeedbackActivities.AppreciationActivity;
import com.teamshunya.silencio.Activities.ShowActivity.Fragments.FeedbackActivities.ComplainActivity;
import com.teamshunya.silencio.Activities.ShowActivity.Fragments.FeedbackActivities.FeedbackActivity;
import com.teamshunya.silencio.Activities.ShowActivity.Fragments.FeedbackActivities.SuggestionActivity;
import com.teamshunya.silencio.R;


public class Feedback extends android.support.v4.app.Fragment implements View.OnClickListener {
    CardView feedback,suggestion,complain,appreciation;
    Fragment fragment;
    public Feedback() {
    }
    private void bindViews(View rootLayout) {
        feedback = (CardView)rootLayout.findViewById(R.id.feedbackCard);
        suggestion = (CardView)rootLayout.findViewById(R.id.suggestionCard);
        complain = (CardView)rootLayout.findViewById(R.id.complainCard);
        appreciation = (CardView)rootLayout.findViewById(R.id.AppreciationCard);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        feedback.setOnClickListener(this);
        suggestion.setOnClickListener(this);
        appreciation.setOnClickListener(this);
        complain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.feedbackCard:
                Intent feedback = new Intent(getContext().getApplicationContext(), FeedbackActivity.class);
                Bundle feed = ActivityOptions.makeCustomAnimation(getContext().getApplicationContext(),R.anim.enter_from_right,R.anim.exit_to_left).toBundle();
                getActivity().startActivity(feedback,feed);
                break;
            case R.id.suggestionCard:
                Intent suggestion = new Intent(getContext().getApplicationContext(), SuggestionActivity.class);
                Bundle suggest = ActivityOptions.makeCustomAnimation(getContext().getApplicationContext(),R.anim.enter_from_right,R.anim.exit_to_left).toBundle();
                getActivity().startActivity(suggestion,suggest);
                break;
            case R.id.AppreciationCard:
                Intent appreciation = new Intent(getContext().getApplicationContext(), AppreciationActivity.class);
                Bundle appreciate = ActivityOptions.makeCustomAnimation(getContext().getApplicationContext(),R.anim.enter_from_right,R.anim.exit_to_left).toBundle();
                getActivity().startActivity(appreciation,appreciate);
                break;
            case R.id.complainCard:
                Intent complaint = new Intent(getContext().getApplicationContext(), ComplainActivity.class);
                Bundle complain = ActivityOptions.makeCustomAnimation(getContext().getApplicationContext(),R.anim.enter_from_right,R.anim.exit_to_left).toBundle();
                getActivity().startActivity(complaint,complain);
                break;
        }
    }
}