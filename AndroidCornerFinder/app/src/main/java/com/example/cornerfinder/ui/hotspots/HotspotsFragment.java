package com.example.cornerfinder.ui.hotspots;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cornerfinder.R;
import com.example.cornerfinder.ui.hotspots.HotspotsAdapter;
import com.example.cornerfinder.ui.hotspots.HotspotsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HotspotsFragment extends Fragment {
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotspots, container, false);

        recyclerView = view.findViewById(R.id.saved_places_recycler_view);

        // Tras identificar el RecyclerView, pasamos a realizar la petición para obtener la info.
        // Si obtenemos respuesta, vamos leyendo todos los datos obtenidos de la url y
// pasandolos de arrays a objetos concretos y añadiendolos individualmente a
// una lista.
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://raw.githubusercontent.com/Bl4nc018/Proyectos-2-trimestre/main/saved_places.json",
                null,
                response -> {
                    List<HotspotsData> allTheHotspots = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject game = response.getJSONObject(i);
                            HotspotsData data = new HotspotsData(game);
                            allTheHotspots.add(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    // Creamos un adaptador con la lista de datos y la actividad asociada.
                    HotspotsAdapter adapter = new HotspotsAdapter(allTheHotspots, getActivity());

                    // Configuramos el RecyclerView con el adaptador y un LinearLayoutManager.
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                },
                error -> Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        // Finalizada la configuración según el tipo de respuesta obtenido,
        // agregamos la solicitud a la cola de Volley para su procesamiento.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
        return view;
    }

}
