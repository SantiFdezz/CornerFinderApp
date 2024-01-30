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
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
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
        if (mapFragment != null) { mapFragment.getMapAsync(this); }


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

        // Crear un BitmapDescriptor morado
        BitmapDescriptor purpleIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET);

        BitmapDescriptor orangeIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);



        LatLng yo = new LatLng(43.362343, -8.411540);
        mMap.addMarker(new MarkerOptions().position(yo).title("A Coruña"));

        LatLng asLapas = new LatLng(43.383636, -8.405864);
        mMap.addMarker(new MarkerOptions().position(asLapas).title("Playa das Lapas").icon(purpleIcon));

        LatLng matadero = new LatLng(43.375622, -8.403764);
        mMap.addMarker(new MarkerOptions().position(matadero).title("Playa de Matadero").icon(purpleIcon));

        LatLng orzan = new LatLng(43.3699, -8.40618);
        mMap.addMarker(new MarkerOptions().position(orzan).title("Playa de Orzán").icon(purpleIcon));

        LatLng riazor = new LatLng(43.3686, -8.4113);
        mMap.addMarker(new MarkerOptions().position(riazor).title("Playa de Riazor").icon(purpleIcon));

        LatLng escondite = new LatLng(43.368200, -8.395100);
        mMap.addMarker(new MarkerOptions().position(escondite).title("Café El Escondite").icon(orangeIcon));

        LatLng cervantes = new LatLng(43.371800, -8.395000);
        mMap.addMarker(new MarkerOptions().position(cervantes).title("Librería Cervantes").icon(orangeIcon));

        LatLng encrucijada = new LatLng(43.370500, -8.395800);
        mMap.addMarker(new MarkerOptions().position(encrucijada).title("La Encrucijada").icon(orangeIcon));


        // Ajustar el zoom para incluir todos los marcadores
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(yo);
        builder.include(asLapas);
        builder.include(matadero);
        builder.include(orzan);
        builder.include(riazor);
        builder.include(escondite);
        builder.include(cervantes);
        builder.include(encrucijada);
        LatLngBounds bounds = builder.build();

        int padding = 200; // Puedes ajustar el espacio alrededor de los marcadores
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.animateCamera(cu);
    }


}
