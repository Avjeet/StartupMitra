package com.teamshunya.silencio.Activities.ShowActivity.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.teamshunya.silencio.Adapter.mDepartureAdapter;
import com.teamshunya.silencio.Models.*;
import com.teamshunya.silencio.R;
import com.teamshunya.silencio.Rest.APIInterface;
import com.teamshunya.silencio.Rest.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Departure extends Fragment {
    private ListView listView;
    private View parentView;
    private List<com.teamshunya.silencio.Models.Departure> departureList;

    public Departure() {
        loadDepartureList();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        final ProgressDialog dialog;
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        departureList = new ArrayList<>();
    }

    private void bindViews(View view) {
        parentView = view.findViewById(R.id.parentt);
        listView = (ListView) view.findViewById(R.id.arrivall_list);
        listView.setDivider(null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Snackbar.make(parentView, "Flight from " + departureList.get(position).getSource() + " is expected to depart at " + departureList.get(position).getEta() + " hrs." + "Kindly check-in from" + departureList.get(position).getGate() + " Counter :)", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    private void loadDepartureList() {
        APIInterface api = ApiClient.getApiService();
        Call<DepartureList> call = api.getFlightDetail();

        call.enqueue(new Callback<DepartureList>() {
            @Override
            public void onResponse(Call<DepartureList> call, Response<DepartureList> response) {
                if (response.isSuccessful()) {
                    try {
                        departureList = response.body().getDeparture();
                        mDepartureAdapter adapter = new mDepartureAdapter(getActivity().getApplicationContext(), departureList);
                        listView.setAdapter(adapter);
                    } catch (Exception e) {
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<DepartureList> call, Throwable t) {
            }
        });


    }
}