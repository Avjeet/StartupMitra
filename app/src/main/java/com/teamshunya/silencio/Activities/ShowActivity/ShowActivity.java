package com.teamshunya.silencio.Activities.ShowActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;


import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.exceptions.CleverTapMetaDataNotFoundException;
import com.clevertap.android.sdk.exceptions.CleverTapPermissionsNotSatisfied;


import com.teamshunya.silencio.Activities.ShowActivity.Fragments.HomeProfile;
import com.teamshunya.silencio.Activities.ShowActivity.Fragments.RecommendorFragment;


import com.teamshunya.silencio.Activities.ShowActivity.SwipableLayout.Weather;
import com.teamshunya.silencio.Classes.CustomFontTextView;
import com.teamshunya.silencio.Classes.StoreSession;
import com.teamshunya.silencio.R;

import com.teamshunya.silencio.Activities.ShowActivity.Fragments.Schemes;


public class ShowActivity extends AppCompatActivity {
    private Fragment fragment;
    Animation slideUpAnimation;
    private FragmentManager fragmentManager;
    CleverTapAPI cleverTap;
    private Toolbar toolbar;
    private CustomFontTextView toolbar_title;
    boolean doubleBackToExitPressedOnce = false;
    private BottomNavigationView bottomNavigation;
    private int selectedMenu = 0;

    public interface QuerySearchInterface {
        public void onQueryChange(String text);
    }

    public interface QRcodeValue {
        public void getQRCode(String qrCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        setContentView(R.layout.activity_show);
        launchSchemes();
        toolbar();
        //open app
        toolbar_title = (CustomFontTextView) findViewById(R.id.toolbar_title);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.bottom_menu);
        fragmentManager = getSupportFragmentManager();
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);
        bottomNavigation.getMenu().getItem(0).setChecked(true);
        selectedMenu = bottomNavigation.getMenu().getItem(0).getItemId();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                CharSequence title=item.getTitle();
                int id = item.getItemId();
                if (selectedMenu == id)
                    return true;
                switch (id) {
                    case R.id.action_schemes:
                        fragment= new Schemes();
                        break;
                    case R.id.action_home:
                        fragment = new RecommendorFragment();
                        break;
                    case R.id.action_homeProfile:
                        fragment =new HomeProfile();
                        break;
                }
                selectedMenu = id;
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                toolbar_title.setText(title);
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

    private void searchViewListeners(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_searchh).getActionView();
        if (null != searchView) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
        }
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                Log.d("search T", newText);
                updateListAdapter(newText);
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                return true;
            }
        };
        try {
            searchView.setOnQueryTextListener(queryTextListener);
        }catch (Exception ignored){}
    }

    private void updateListAdapter(String newText) {
        if (fragment instanceof QuerySearchInterface) {
            QuerySearchInterface querySearchInterface = (QuerySearchInterface) fragment;
            querySearchInterface.onQueryChange(newText);
        }
    }

    private void toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.cloud);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Weather();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                transaction.replace(R.id.main_container, fragment)
                        .commit();
                bottomNavigation.setVisibility(View.GONE);
                toolbar_title.setText(getResources().getString(R.string.weater));
            }
        });
        setTitle("  ");
        toolbar.findViewById(R.id.toolbar_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), ShowActivity.class));
            }
        });

    }

    private void launchSchemes() {
        fragment = new Schemes();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.add(R.id.main_container, fragment).commit();



    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        fragment = new Schemes();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);

        transaction.replace(R.id.main_container, fragment)
                .commit();
        bottomNavigation.setVisibility(View.VISIBLE);
        toolbar_title.setText(getString(R.string.app_name));
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
                                Fragment fragment = new HomeProfile();
                                FragmentManager manager = getSupportFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                                transaction.replace(R.id.main_container, fragment)
                                        .commit();

                                bottomNavigation.setVisibility(View.GONE);
                                toolbar_title.setText(getResources().getString(R.string.profile));
                                break;
                            case R.id.SOS:
                                startActivity(new Intent(ShowActivity.this,SOS.class));
                                break;
                        }
                        return onOptionsItemSelected(item);
                    }
                });
        searchViewListeners(menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("camera", "resume");
        if (fragment instanceof QRcodeValue) {
            Log.d("camera", "inside "+StoreSession.getInstance().readPreferencesString("qrCode", ""    ));
            QRcodeValue querySearchInterface = (QRcodeValue) fragment;
            querySearchInterface.getQRCode(StoreSession.getInstance().readPreferencesString("qrCode", ""    ));
        }
    }
}
