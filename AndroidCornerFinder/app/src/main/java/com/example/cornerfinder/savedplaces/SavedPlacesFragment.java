package com.example.cornerfinder.savedplaces;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cornerfinder.R;
import com.example.cornerfinder.Server;
import com.example.cornerfinder.summermode.SummerModeAdapter;
import com.example.cornerfinder.summermode.SummerModeData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SavedPlacesFragment extends Fragment {
    private RecyclerView recyclerView;
    private SavedPlacesAdapter adapter;
    private List<SavedPlacesData> savedPlacesDataList;

    private RequestQueue requestQueue;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SavedPlacesFragment() {
        // Required empty public constructor
    }

    public static SavedPlacesFragment newInstance(String param1, String param2) {
        SavedPlacesFragment fragment = new SavedPlacesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(this);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_saved_places, container, false);

        savedPlacesDataList = new ArrayList<>();
        adapter = new SavedPlacesAdapter(savedPlacesDataList, (Activity) getContext());
        recyclerView = view.findViewById(R.id.saved_places_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest request = new JsonArrayRequest
                (Request.Method.GET,
                        "https://raw.githubusercontent.com/Bl4nc018/Proyectos-2-trimestre/main/saved_places.json",
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                List<SavedPlacesData> allTheSavedPlaces = new ArrayList<>();
                                for(int i=0; i<response.length(); i++) {
                                    try {
                                        JSONObject places = response.getJSONObject(i);
                                        SavedPlacesData data = new SavedPlacesData(places);
                                        savedPlacesDataList.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        error.printStackTrace();
                    }
                });
        queue.add(request);
    }
}
