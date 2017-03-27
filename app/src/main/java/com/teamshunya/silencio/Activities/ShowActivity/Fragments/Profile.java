package com.teamshunya.silencio.Activities.ShowActivity.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.teamshunya.silencio.Classes.CircleTransform;
import com.teamshunya.silencio.Classes.CustomFontTextView;
import com.teamshunya.silencio.R;

import java.util.Arrays;
import java.util.List;

public class Profile extends android.support.v4.app.Fragment {
    public static final String TAG = "LoginActivity";
    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN = 1;
    private FirebaseAuth mFirebaseAuth;
    private CustomFontTextView username, emailID;
    ImageView imageView;
    Button Logout;

    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseUser user;

    public Profile() {


        mFirebaseAuth = FirebaseAuth.getInstance();
        final List<AuthUI.IdpConfig> providers = Arrays.asList(

                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()


        );
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    onSignedInInitialize(user.getDisplayName(), user.getPhotoUrl(), user.getEmail());
                } else {
                    onSignedOutCleanup();

                    startActivityForResult(
                            AuthUI.getInstance()

                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(providers
                                    )
                                    .setLogo(R.drawable.logooo)
                                    .setTheme(R.style.profile)
                                    .build(), RC_SIGN_IN);
                }
            }

            private void onSignedInInitialize(String displayName, Uri photoUrl, String email) {
                username.setText(displayName);
                emailID.setText(email);
                Glide.with(getContext()).load(photoUrl).transform(new CircleTransform(getContext())).into(imageView);
            }
        };
    }


    private void onSignedOutCleanup() {
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_profile, container, false);
        username = (CustomFontTextView) view.findViewById(R.id.username);
        Logout = (Button) view.findViewById(R.id.logut);
        emailID = (CustomFontTextView) view.findViewById(R.id.emailid);
        imageView = (ImageView) view.findViewById(R.id.imageView);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
