package com.teamshunya.silencio.ShowActivity.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.teamshunya.silencio.R;

import java.util.Arrays;
import java.util.List;

public class Profile  extends android.support.v4.app.Fragment  {
    public static final String TAG ="LoginActivity";
    public static final String ANONYMOUS ="anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT =1000;
    public static final int RC_SIGN_IN =1;
    private FirebaseAuth mFirebaseAuth;
    TextView username;
    ImageView imageView;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public Profile() {

        mFirebaseAuth = FirebaseAuth.getInstance();
        final List<AuthUI.IdpConfig> providers = Arrays.asList(

                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()

        );
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user !=null){
                    onSignedInInitialize(user.getDisplayName(),user.getPhotoUrl());
                }
                else {
                    onSignedOutCleanup();

                    startActivityForResult(
                            AuthUI.getInstance()

                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(
                                            providers
                                    )

                                    .setLogo(R.drawable.logo)
                                    .setTheme(R.style.profile)
                                    .build(),RC_SIGN_IN);


                }
            }

            private void onSignedInInitialize(String displayName, Uri photoUrl) {
                    username.setText(displayName);
                Glide.with(getContext()).load(photoUrl).into(imageView);
            }
            };
        };


    private void onSignedOutCleanup() {

    }


    

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();

        View view =  lf.inflate(R.layout.fragment_profile, container, false);
        username = (TextView) view.findViewById(R.id.username); //
        imageView = (ImageView) view.findViewById(R.id.imageView); //
        return view;
    }
}
