package com.teamshunya.silencio.Activities.ShowActivity.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.teamshunya.silencio.Activities.ShowActivity.ShowActivity;
import com.teamshunya.silencio.Adapter.mDepartureAdapter;
import com.teamshunya.silencio.Adapter.mOfferAdapter;
import com.teamshunya.silencio.Adapter.myArrivalAdapter;
import com.teamshunya.silencio.Models.ArrivalList;
import com.teamshunya.silencio.R;
import com.teamshunya.silencio.Rest.APIInterface;
import com.teamshunya.silencio.Rest.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Arrival extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener, ShowActivity.QuerySearchInterface {
    private ListView listView;
    private View parentView;
    private View view;
    private ProgressDialog dialog;
    private List<com.teamshunya.silencio.Models.Arrival> arrivalList;
    private myArrivalAdapter adapter;
    private SwipeRefreshLayout arrivalSwipeRefreshLayout;
    private Activity activity;

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
        arrivalSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.arrival_swip);
        arrivalSwipeRefreshLayout.setOnRefreshListener(this);

        listView = (ListView) view.findViewById(R.id.arrival_list);
        listView.setDivider(null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(parentView, "Flight from " + arrivalList.get(position).getSource() + " is expected to be here at " + arrivalList.get(position).getEta() + " hrs." + "Baggage will be available at  " + arrivalList.get(position).getGate() + " Counter :)", Snackbar.LENGTH_LONG).show();
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onRefresh() {
        loadArrivalList();
        arrivalSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onQueryChange(String text) {
        List<com.teamshunya.silencio.Models.Arrival> filteredList = new ArrayList<>();
        for (com.teamshunya.silencio.Models.Arrival arrival : arrivalList) {
            if (arrival.getSource().toLowerCase().contains(text.toLowerCase())) {
                if (!filteredList.contains(arrival))
                    filteredList.add(arrival);
            }
            else if (arrival.getDestination().toLowerCase().contains(text.toLowerCase())) {
                if (!filteredList.contains(arrival))
                    filteredList.add(arrival);
            }
            else if (arrival.getFlightNo().toLowerCase().contains(text.toLowerCase())) {
                if (!filteredList.contains(arrival))
                    filteredList.add(arrival);
            }
        }
        listView = (ListView) activity.findViewById(R.id.arrival_list);
        adapter = new myArrivalAdapter(activity.getApplicationContext(), filteredList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }
}