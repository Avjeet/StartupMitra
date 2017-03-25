package com.teamshunya.silencio.Activities.ShowActivity.SwipableLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.teamshunya.silencio.Classes.GPSTracker;
import com.teamshunya.silencio.R;

import java.util.Map;

public class Maps extends Fragment implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    GoogleMap mGoogleMap;
    private static final long GEO_DURATION = 60 * 60 * 1000;
    private static final String GEOFENCE_REQ_ID = "My Geofence";
    private static final float GEOFENCE_RADIUS = 500.0f;
    private int distance;

    MapView mMapView;
    View mView;
    private GoogleApiClient googleApiClient;
    GPSTracker gps;
    double latitude,longitude;
    double lat = 12.9218780;
    double llong =77.6200830;
    public Maps() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createGoogleApi();
        gps = new GPSTracker(getContext());
        if(gps.canGetLocation()){
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();}

    }

    private void createGoogleApi() {
        Log.d("Name", "createGoogleApi()");
        if ( googleApiClient == null ) {
            googleApiClient = new GoogleApiClient.Builder(getContext().getApplicationContext())
                    .addConnectionCallbacks( this )
                    .addOnConnectionFailedListener( this )
                    .addApi( LocationServices.API )
                    .build();
        }
    }
    @Override
    public void onStart() {
        super.onStart();

        // Call GoogleApiClient connection when starting the Activity
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();

        // Disconnect GoogleApiClient when stopping Activity
        googleApiClient.disconnect();
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView)mView.findViewById(R.id.map);
        if (mMapView!=null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_maps, container, false);
        return mView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        MarkerOptions newMarker = new MarkerOptions();
        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).title("You are here").snippet("Enjoy your day"));
        CameraPosition Liberty = CameraPosition.builder().target(new LatLng(latitude,longitude)).zoom(16).bearing(0).tilt(20).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));

        googleMap.addCircle(new CircleOptions()
                .strokeWidth(1)
                .center(new LatLng(latitude, longitude))
                .radius(300)
                .fillColor(0x5533cccc));


    }

    // Create a Geofence
    private Geofence createGeofence(LatLng latLng, float radius ) {
        Log.d("Maps", "createGeofence");
        return new Geofence.Builder()
                .setRequestId(GEOFENCE_REQ_ID)
                .setCircularRegion( latLng.latitude, latLng.longitude, radius)
                .setExpirationDuration( GEO_DURATION )
                .setTransitionTypes( Geofence.GEOFENCE_TRANSITION_ENTER
                        | Geofence.GEOFENCE_TRANSITION_EXIT )
                .build();
    }
    private GeofencingRequest createGeofenceRequest(Geofence geofence ) {
        Log.d("Maps", "createGeofenceRequest");
        return new GeofencingRequest.Builder()
                .setInitialTrigger( GeofencingRequest.INITIAL_TRIGGER_ENTER )
                .addGeofence( geofence )
                .build();
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    public void onMarkerDragStart(Marker marker) {
    }



    public void onMarkerDrag(Marker marker) {
    }



    public void onMarkerDragEnd(Marker marker) {
        LatLng dragPosition = marker.getPosition();
        double latitude = dragPosition.latitude;
        double longitude = dragPosition.longitude;
    }

}