package com.example.minhopark.View;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.MinhoPark.R;
import com.example.MinhoPark.databinding.ActivityMaps2Binding;
import com.example.minhopark.View.directionHelpers.FetchURL;
import com.example.minhopark.View.directionHelpers.TaskLoadedCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private GoogleMap mMap;
    private ActivityMaps2Binding binding;
    private Polyline currentPolyline;

    //TODO: pus isto aqui por ser um exemplo, tem que ser feito load a partir da BD
    private LatLng braga = new LatLng(41.5510583, -8.4280045);
    private LatLng barcelos =  new LatLng(41.5314496, -8.6192306);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMaps2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String url = getUrl(braga, barcelos, "driving");
        new FetchURL(MapsActivity.this).execute(url, "driving");
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        adicionaMarker(mMap, braga, "Braga");
        adicionaMarker(mMap, barcelos, "Barcelos");

        mMap.moveCamera(CameraUpdateFactory.newLatLng(braga));
    }


    public LatLng adicionaMarker(GoogleMap mMap, double latitude, double longitude, String name) {
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title(name));

        return latLng;
    }

    public void adicionaMarker(GoogleMap mMap, LatLng latLng, String name) {
        mMap.addMarker(new MarkerOptions().position(latLng).title(name));
    }


    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null) {
            currentPolyline.remove();
        }
        else {
            currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
        }
    }
}