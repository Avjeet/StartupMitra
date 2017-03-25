package com.teamshunya.silencio.Activities.ShowActivity.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.teamshunya.silencio.Adapter.mDepartureAdapter;
import com.teamshunya.silencio.Classes.CustomFontTextView;
import com.teamshunya.silencio.Classes.StoreSession;
import com.teamshunya.silencio.Models.DepartureList;
import com.teamshunya.silencio.R;
import com.teamshunya.silencio.Rest.APIInterface;
import com.teamshunya.silencio.Rest.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Departure extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ListView listView;
    private View parentView;
    private SwipeRefreshLayout departureSwipeRefreshLayout;
    private CustomFontTextView source,departuree,destination,flightnumber,gatenumber,seatnumber;
    private ImageView cancel;
    String PNR;
    EditText userInput;
    private List<com.teamshunya.silencio.Models.Departure> departureList;
    private List<com.teamshunya.silencio.Models.Departure> myList;
    LinearLayout layout;

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
        View promptsView = li.inflate(R.layout.pnr_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(promptsView);
        userInput = (EditText) promptsView
                .findViewById(R.id.pnr);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String pnr = userInput.getText().toString();
                                fetchInfoByPNR(pnr);

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
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
        APIInterface apiInterface = ApiClient.getApiService();
        Call<com.teamshunya.silencio.Models.Departure> call = apiInterface.getmyDetail(s);
        call.enqueue(new Callback<com.teamshunya.silencio.Models.Departure>() {
            @Override
            public void onResponse(Call<com.teamshunya.silencio.Models.Departure> call, Response<com.teamshunya.silencio.Models.Departure> response) {
                StoreSession.getInstance().savePreferencesString("PNR", s);
                com.teamshunya.silencio.Models.Departure model = response.body().getDeparture();

                layout.setVisibility(View.VISIBLE);
                source.setText(model.getSource());
                destination.setText(model.getDestination());
                seatnumber.setText(model.getSeat());
                gatenumber.setText(model.getGate());
                departuree.setText(model.getEta());
                flightnumber.setText(model.getFlightNo());

            }

            @Override
            public void onFailure(Call<com.teamshunya.silencio.Models.Departure> call, Throwable t) {

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
        departureList = new ArrayList<>();
    }

    private void bindViews(View view) {
        layout = (LinearLayout) view.findViewById(R.id.layout);
        destination = (CustomFontTextView)view.findViewById(R.id.destination);
        departuree = (CustomFontTextView)view.findViewById(R.id.departure);
        flightnumber =(CustomFontTextView)view.findViewById(R.id.flightnumber);
        gatenumber = (CustomFontTextView)view.findViewById(R.id.gatenumber);
        seatnumber = (CustomFontTextView)view.findViewById(R.id.seatnumber);
        parentView = view.findViewById(R.id.parentt);
        source = (CustomFontTextView) view.findViewById(R.id.src);
        departureSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.departure_swip);
        departureSwipeRefreshLayout.setOnRefreshListener(this);
        listView = (ListView) view.findViewById(R.id.arrivall_list);
        listView.setDivider(null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlert(departureList.get(position));
//                Snackbar.make(parentView, "Flight from " + departureList.get(position).getSource() + " is expected to depart at " + departureList.get(position).getEta() + " hrs." + "Kindly check-in from" + departureList.get(position).getGate() + " Counter :)", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    private void showAlert(com.teamshunya.silencio.Models.Departure departure) {
        final Dialog dialog = new Dialog((getActivity()));
        dialog.setContentView(R.layout.dialog_custom);
        CustomFontTextView text = (CustomFontTextView) dialog.findViewById(R.id.text_details);
        CustomFontTextView ok = (CustomFontTextView) dialog.findViewById(R.id.ok);
        dialog.setTitle(departure.getSource());
        text.setText(departure.getEta());
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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

    @Override
    public void onRefresh() {
        loadDepartureList();
        departureSwipeRefreshLayout.setRefreshing(false);

    }
}