package com.teamshunya.silencio.Activities.ShowActivity.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.squareup.picasso.Picasso;
import com.teamshunya.silencio.Activities.ShowActivity.QRCodeActivi;
import com.teamshunya.silencio.Activities.ShowActivity.ShowActivity;
import com.teamshunya.silencio.Adapter.mDepartureAdapter;
import com.teamshunya.silencio.Classes.CustomFontTextView;
import com.teamshunya.silencio.Classes.StoreSession;
import com.teamshunya.silencio.Models.DepartureList;
import com.teamshunya.silencio.Models.PersonalDetail;
import com.teamshunya.silencio.R;
import com.teamshunya.silencio.Rest.APIInterface;
import com.teamshunya.silencio.Rest.ApiClient;
import com.teamshunya.silencio.Rest.MyDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Schemes extends Fragment  {


    public Schemes() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }







}




