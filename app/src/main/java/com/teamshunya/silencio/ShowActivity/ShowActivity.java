package com.teamshunya.silencio.ShowActivity;

import android.support.annotation.NonNull;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //open app
        BottomNavigationView bottomNavigation = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.bottom_menu);
        final View includedLayout = findViewById(R.id.cardBoarding);
        fragmentManager = getSupportFragmentManager();
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.action_search:
                        includedLayout.setVisibility(View.VISIBLE);
                        fragment = new Arrival();
                        break;
                    case R.id.action_cart:
                        includedLayout.setVisibility(View.VISIBLE);
                        fragment = new Departure();
                        break;
                    case R.id.action_hot_deals:
                        includedLayout.setVisibility(View.VISIBLE);
                        fragment = new DealsFragment();
                        break;
                    case R.id.action_feedback:
                        includedLayout.setVisibility(View.GONE);
                        fragment = new Feedback();
                        break;
                    case R.id.action_profile:
                        includedLayout.setVisibility(View.GONE);
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





        //back button
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


    }




    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

 @Override

 public boolean onOptionsItemSelected(MenuItem item) {

     switch (item.getItemId()) {
//feedback
         case R.id.menu_bookmark:



     }
     return true;
 }



}
