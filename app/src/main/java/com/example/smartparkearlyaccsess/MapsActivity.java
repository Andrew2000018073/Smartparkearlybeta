package com.example.smartparkearlyaccsess;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.smartparkearlyaccsess.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        LatLng par1 = new LatLng(-7.837314296574191, 110.36745505183767);
        googleMap.addMarker(new MarkerOptions().position(par1).title("Tempat Parkir STTKD"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(par1));

        LatLng par2 = new LatLng(-7.833694177568202, 110.3824630682838);
        googleMap.addMarker(new MarkerOptions().position(par2).title("Parkir Selatan UAD Kampus 4"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(par2));

        LatLng par3 = new LatLng(-7.797144449758793, 110.36430582595523);
        googleMap.addMarker(new MarkerOptions().position(par3).title("Kantong Parkir Beskalan"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(par3));

        LatLng par4 = new LatLng(-7.826969885703536, 110.39427679711969);
        googleMap.addMarker(new MarkerOptions().position(par4).title("Parkir Omah Dhuwur"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(par4));

        LatLng par5 = new LatLng(-7.7825716043524364, 110.38004298607459);
        googleMap.addMarker(new MarkerOptions().position(par5).title("Parkiran Timur Galeria Mall"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(par5));

        float zoomLevel = 16.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(par1, zoomLevel));
    }
}