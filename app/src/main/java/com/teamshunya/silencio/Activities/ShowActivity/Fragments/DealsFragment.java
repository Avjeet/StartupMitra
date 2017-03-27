package com.teamshunya.silencio.Activities.ShowActivity.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.teamshunya.silencio.Activities.ShowActivity.ShowActivity;
import com.teamshunya.silencio.Adapter.mDepartureAdapter;
import com.teamshunya.silencio.Adapter.mOfferAdapter;
import com.teamshunya.silencio.Models.Offer;
import com.teamshunya.silencio.Models.OfferList;
import com.teamshunya.silencio.R;
import com.teamshunya.silencio.Rest.APIInterface;
import com.teamshunya.silencio.Rest.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealsFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener, ShowActivity.QuerySearchInterface {
    private ListView listView;
    private View parentView;
    private List<Offer> offerList;
    private mOfferAdapter adapter;
    private SwipeRefreshLayout dealsSwipeRefreshLayout;
    private Activity activity;

    public DealsFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_deals, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        loadOfferList();
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        offerList = new ArrayList<>();
    }

    private void loadOfferList() {
        APIInterface api = ApiClient.getApiService();
        Call<OfferList> call = api.getOfferDetail();
        call.enqueue(new Callback<OfferList>() {
            @Override
            public void onResponse(Call<OfferList> call, Response<OfferList> response) {
                if (response.isSuccessful()) {
                    try {
                        offerList = response.body().getOffer();
                        adapter = new mOfferAdapter(getActivity().getApplicationContext(), offerList);
                        listView.setAdapter(adapter);
                    } catch (Exception e) {
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<OfferList> call, Throwable t) {
            }
        });

    }

    private void bindViews(View view) {
        loadOfferList();
        parentView = view.findViewById(R.id.parentLayout);
        dealsSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.deals_swip);
        dealsSwipeRefreshLayout.setOnRefreshListener(this);
        listView = (ListView) view.findViewById(R.id.arrival_list);
        listView.setDivider(null);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Snackbar.make(parentView, "Offer of" + offerList.get(position).getName() + " is avaible at  " + offerList.get(position).getPromocode()  + "Expiry Date is " + offerList.get(position).getExpiry() + ".Enjoy :)", Snackbar.LENGTH_LONG).show();
//            }
//        });


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onRefresh() {
        loadOfferList();
        dealsSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onQueryChange(String text) {
        List<com.teamshunya.silencio.Models.Offer> filteredList = new ArrayList<>();
        for (com.teamshunya.silencio.Models.Offer offer : offerList) {
            if (offer.getName().toLowerCase().contains(text.toLowerCase())) {
                if (!filteredList.contains(offer))
                    filteredList.add(offer);
            }
            else if (offer.getTag().toLowerCase().contains(text.toLowerCase())) {
                if (!filteredList.contains(offer))
                    filteredList.add(offer);
            }
        }
        listView = (ListView) activity.findViewById(R.id.arrival_list);
        adapter = new mOfferAdapter(activity.getApplicationContext(), filteredList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }
}