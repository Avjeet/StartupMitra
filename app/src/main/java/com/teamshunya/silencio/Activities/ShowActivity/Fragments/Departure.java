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


public class Departure extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ShowActivity.QuerySearchInterface,ShowActivity.QRcodeValue {
    private ListView listView;
    private View parentView;
    View promptsView;
    private SwipeRefreshLayout departureSwipeRefreshLayout;
    private CustomFontTextView source, departure_time, destination, flightnumber, gatenumber, seatnumber;
    private ImageView cancel;
    EditText userInput;
    private static String pnrQR = "";
    private List<com.teamshunya.silencio.Models.Departure> departureList;
    private List<com.teamshunya.silencio.Models.Departure> myList;
    private mDepartureAdapter adapter;
    LinearLayout layout;
    Activity activity;

    public Departure() {
        loadDepartureList();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    private void alertBox() {
        LayoutInflater li = LayoutInflater.from(getContext());
        promptsView = li.inflate(R.layout.pnr_dialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptsView);
        userInput = (EditText) promptsView.findViewById(R.id.pnr);
        userInput.setText(pnrQR);
        alertDialogBuilder
                .setPositiveButton(getResources().getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String pnr = userInput.getText().toString();
                                fetchInfoByPNR(pnr);

                            }
                        })
                .setCancelable(true)
                .setNegativeButton(getResources().getString(R.string.CANCEL),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.dismiss();

                            }
                        })
                .setNeutralButton(getResources().getString(R.string.SCAN),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
                                startActivity(new Intent(activity, QRCodeActivi.class));
                            }
                        })

        ;
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

            }
        });
    }

    private void fetchInfoByPNR(final String s) {
        APIInterface apiInterface = MyDetail.getApiService();
        Call<PersonalDetail> call = apiInterface.getmeDetail(s);
        call.enqueue(new Callback<PersonalDetail>() {
            @Override
            public void onResponse(Call<PersonalDetail> call, Response<PersonalDetail> response) {
                StoreSession.getInstance().savePreferencesString("PNR", s);
                PersonalDetail model = response.body();
                try {

                    layout.setVisibility(View.VISIBLE);
                    source.setText(model.getSource());
                    destination.setText(model.getDestination());
                    departure_time.setText(model.getEta());
                    flightnumber.setText(model.getFlightNo());
                    gatenumber.setText(model.getGate());
                    seatnumber.setText(model.getSeat());
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<PersonalDetail> call, Throwable t) {

            }


        });
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        String pnr = StoreSession.getInstance().readPreferencesString("PNR", "");
        if (pnr.isEmpty()) {
            alertBox();
        } else {
            fetchInfoByPNR(pnr);

        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreSession.getInstance().deletePreferencesString("PNR", "");
                layout.setVisibility(View.GONE);
            }
        });
        departureList = new ArrayList<>();
    }

    private void bindViews(View view) {
        layout = (LinearLayout) view.findViewById(R.id.layout);
        destination = (CustomFontTextView) view.findViewById(R.id.destination);
//        departure_time = (CustomFontTextView) view.findViewById(R.id.departure_time);
        flightnumber = (CustomFontTextView) view.findViewById(R.id.flightnumber);
        gatenumber = (CustomFontTextView) view.findViewById(R.id.gatenumber);
        seatnumber = (CustomFontTextView) view.findViewById(R.id.seatnumber);
        parentView = view.findViewById(R.id.parentt);
        source = (CustomFontTextView) view.findViewById(R.id.src);
        cancel = (ImageView) view.findViewById(R.id.cancel);
        departureSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.departure_swip);
        departureSwipeRefreshLayout.setOnRefreshListener(this);
        listView = (ListView) view.findViewById(R.id.arrivall_list);
        listView.setDivider(null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Snackbar.make(parentView, "Flight from " + departureList.get(position).getSource() + " is expected to depart at " + departureList.get(position).getEta() + " hrs." + "Kindly check-in from" + departureList.get(position).getGate() + " Counter :)", Snackbar.LENGTH_LONG).show();
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
                        adapter = new mDepartureAdapter(getActivity().getApplicationContext(), departureList);
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

    @Override
    public void onRefresh() {
        loadDepartureList();
        departureSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onQueryChange(String text) {
        List<com.teamshunya.silencio.Models.Departure> filteredDepartureList = new ArrayList<>();
        for (com.teamshunya.silencio.Models.Departure departure : departureList) {
            if (departure.getSource().toLowerCase().contains(text.toLowerCase())) {
                if (!filteredDepartureList.contains(departure))
                    filteredDepartureList.add(departure);
            } else if (departure.getDestination().toLowerCase().contains(text.toLowerCase())) {
                if (!filteredDepartureList.contains(departure))
                    filteredDepartureList.add(departure);
            } else if (departure.getFlightNo().toLowerCase().contains(text.toLowerCase())) {
                if (!filteredDepartureList.contains(departure))
                    filteredDepartureList.add(departure);
            }
        }
        listView = (ListView) activity.findViewById(R.id.arrivall_list);
        adapter = new mDepartureAdapter(activity.getApplicationContext(), filteredDepartureList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    @Override
    public void getQRCode(String qrCode) {
        Log.d("camera", "departure"+qrCode);
        pnrQR = qrCode;
        alertBox();
    }
}