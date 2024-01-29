package com.example.cornerfinder;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddlocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddlocationFragment extends Fragment implements OnMapReadyCallback{
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private CheckBox checkBox1;
    private Button addButton;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddlocationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddlocationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddlocationFragment newInstance(String param1, String param2) {
        AddlocationFragment fragment = new AddlocationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Reference UI elements
        View view = inflater.inflate(R.layout.fragment_addlocation, container, false);
        nameEditText = view.findViewById(R.id.placeName);
        descriptionEditText = view.findViewById(R.id.description);
        checkBox1 = view.findViewById(R.id.checkbox1);
        checkBox2 = view.findViewById(R.id.checkbox2);
        checkBox3 = view.findViewById(R.id.checkbox3);
        checkBox4 = view.findViewById(R.id.checkbox4);
        checkBox5 = view.findViewById(R.id.checkbox5);
        checkBox6 = view.findViewById(R.id.checkbox6);
        addButton = view.findViewById(R.id.tags);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from UI elements
                String name = nameEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                boolean isCheckBox1Checked = checkBox1.isChecked();
                boolean isCheckBox2Checked = checkBox2.isChecked();
                boolean isCheckBox3Checked = checkBox3.isChecked();
                boolean isCheckBox4Checked = checkBox4.isChecked();
                boolean isCheckBox5Checked = checkBox5.isChecked();
                boolean isCheckBox6Checked = checkBox6.isChecked();
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(43.362343,-8.411540);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in A Coruña"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}