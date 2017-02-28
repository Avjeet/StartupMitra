package com.teamshunya.silencio.ShowActivity.Fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamshunya.silencio.R;



@SuppressLint("NewApi")
public class DealsFragment extends android.support.v4.app.Fragment  {
    public DealsFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_deals, container, false);
    }
}