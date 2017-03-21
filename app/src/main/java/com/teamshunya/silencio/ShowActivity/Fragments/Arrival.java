package com.teamshunya.silencio.ShowActivity.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.teamshunya.silencio.Adapter.myArrivalAdapter;
import com.teamshunya.silencio.Models.ArrivalList;
import com.teamshunya.silencio.R;
import com.teamshunya.silencio.Rest.APIInterface;
import com.teamshunya.silencio.Rest.ApiClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Arrival extends android.support.v4.app.Fragment {
    private ListView listView;
    private View parentView;
    private ProgressDialog dialog;
    private List<com.teamshunya.silencio.Models.Arrival> arrivalList;
    private myArrivalAdapter adapter;

    public Arrival() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        final ProgressDialog dialog;
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_arrival, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        loadArrivalList();
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        arrivalList = new ArrayList<>();
    }


    private void bindViews(View view) {
        loadArrivalList();
        parentView = view.findViewById(R.id.parentLayout);
        listView = (ListView) view.findViewById(R.id.arrival_list);
        listView.setDivider(null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(parentView, "Flight from " + arrivalList.get(position).getSource() + " is expected to be here at " + arrivalList.get(position).getEta() + " hrs." + "Collect your baggage from " + arrivalList.get(position).getGate() + " Counter :)", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    private void loadArrivalList() {
        APIInterface api = ApiClient.getApiService();
        Call<ArrivalList> call = api.getFlightDetails();
        call.enqueue(new Callback<ArrivalList>() {
            @Override
            public void onResponse(Call<ArrivalList> call, Response<ArrivalList> response) {
                if (response.isSuccessful()) {
                    try {
                        arrivalList = response.body().getArrivals();
                        adapter = new myArrivalAdapter(getActivity().getApplicationContext(), arrivalList);
                        listView.setAdapter(adapter);
                    } catch (Exception e) {
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<ArrivalList> call, Throwable t) {
            }
        });


    }


}