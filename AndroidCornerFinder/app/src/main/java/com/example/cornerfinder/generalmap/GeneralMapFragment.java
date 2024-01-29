package com.example.cornerfinder.generalmap;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cornerfinder.AddlocationFragment;
import com.example.cornerfinder.MainActivity;
import com.example.cornerfinder.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GeneralMapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private boolean isButton2Visible = false;

    public GeneralMapFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Reference UI elements
        View view = inflater.inflate(R.layout.fragment_generalmap, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        Button addButton = view.findViewById(R.id.addButton);
        Button addButton2 = view.findViewById(R.id.addButton2);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton2Visible) {
                    addButton2.setVisibility(View.INVISIBLE);
                } else {
                    addButton2.setVisibility(View.VISIBLE);
                }

                isButton2Visible = !isButton2Visible;
            }
        });


        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creado un intent para ir al mainActivity e inflar alli el fragmento.
                Intent intent = new Intent(requireActivity(), MainActivity.class);
                intent.setAction("ACTION_START_ADD_LOCATION");

                // Iniciar la MainActivity
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(43.362343,-8.411540);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in A Coruña"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
