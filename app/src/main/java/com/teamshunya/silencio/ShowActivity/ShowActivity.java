package com.teamshunya.silencio.ShowActivity;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.exceptions.CleverTapMetaDataNotFoundException;
import com.clevertap.android.sdk.exceptions.CleverTapPermissionsNotSatisfied;


import com.teamshunya.silencio.R;
import com.teamshunya.silencio.ShowActivity.Fragments.Arrival;
import com.teamshunya.silencio.ShowActivity.Fragments.DealsFragment;
import com.teamshunya.silencio.ShowActivity.Fragments.Departure;
import com.teamshunya.silencio.ShowActivity.Fragments.Feedback;
import com.teamshunya.silencio.ShowActivity.Fragments.Profile;

public class ShowActivity extends AppCompatActivity {
    private Fragment fragment;
    private FragmentManager fragmentManager;
    CleverTapAPI cleverTap;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        launchDeparture();
        toolbar();

        //open app

        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.bottom_menu);
        fragmentManager = getSupportFragmentManager();
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);
        bottomNavigation.getMenu().getItem(2).setChecked(true);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.action_search:
                        fragment = new Arrival();
                        break;
                    case R.id.action_cart:
                        fragment = new Departure();
                        break;
                    case R.id.action_hot_deals:
                        fragment = new DealsFragment();
                        break;
                    case R.id.action_feedback:
                        fragment = new Feedback();
                        break;
                    case R.id.action_profile:
                        fragment = new Profile();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });

        try {
            cleverTap = CleverTapAPI.getInstance(getApplicationContext());
        } catch (CleverTapMetaDataNotFoundException e) {
            // thrown if you haven't specified your CleverTap Account ID or Token in your AndroidManifest.xml
        } catch (CleverTapPermissionsNotSatisfied e) {
            // thrown if you haven’t requested the required permissions in your AndroidManifest.xml
        }
    }


    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
    }


    private void launchDeparture() {
        Fragment fragment = new Departure();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_container, fragment).commit();


    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.menu_profile:
                                Fragment fragment = new Profile();
                                FragmentManager manager = getSupportFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.add(R.id.main_container, fragment).commit();

                        }


                        return onOptionsItemSelected(item);
                    }
                });

        return true;
    }




}
