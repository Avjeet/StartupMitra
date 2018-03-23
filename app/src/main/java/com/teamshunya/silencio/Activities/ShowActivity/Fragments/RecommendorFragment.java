package com.teamshunya.silencio.Activities.ShowActivity.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamshunya.silencio.R;

import static com.facebook.FacebookSdk.getApplicationContext;


public class RecommendorFragment extends Fragment {



    private RecyclerView mRecyclerView;
    private CategoriesAdapter categoriesadapter;
    private CategoriesAdapter subCategoriesAdapter;
    private RecyclerView mSubRecyclerView;

    public RecommendorFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         categoriesadapter = new CategoriesAdapter(false);
         subCategoriesAdapter= new CategoriesAdapter(true);
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_recommendor, container, false);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.rv_categories);
        mSubRecyclerView=(RecyclerView)v.findViewById(R.id.rv_sub_categories);

        return v;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(categoriesadapter);

        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);

        mSubRecyclerView.setLayoutManager(LayoutManager);
        mSubRecyclerView.setAdapter(subCategoriesAdapter );

    }
}
