package com.teamshunya.silencio.ShowActivity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.exceptions.CleverTapMetaDataNotFoundException;
import com.clevertap.android.sdk.exceptions.CleverTapPermissionsNotSatisfied;


import com.teamshunya.silencio.R;
import com.teamshunya.silencio.ShowActivity.Fragments.DealsFragment;

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

        fragmentManager = getSupportFragmentManager();
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.action_search:
                        fragment = new DealsFragment();
                        break;
                    case R.id.action_cart:
                        fragment = new DealsFragment();
                        break;
                    case R.id.action_hot_deals:
                        fragment = new DealsFragment();
                        break;
                    case R.id.action_feedback:
                        fragment = new DealsFragment();
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
