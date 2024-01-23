package com.example.cornerfinder.summermode;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cornerfinder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SummerModeActivity extends AppCompatActivity {
    private Activity activity=this;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_summermode); // Aquí obtenemos una referencia al RecyclerView desde el diseño

        JsonArrayRequest request = new JsonArrayRequest( // Creamos una solicitud GET usando Volley para obtener un JSONArray de nuestra URL.
                Request.Method.GET,
                "https://raw.githubusercontent.com/Bl4nc018/Proyectos-2-trimestre/main/beaches.json",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {  // Procedemos a procesar la respuesta JSON y crear una lista de objetos GameData
                                                                  // donde estarán los datos de la url.
                        List<SummerModeData> allTheBeaches = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try { // Convertimos cada objeto JSON en un objeto GameData.
                                JSONObject game = response.getJSONObject(i);
                                SummerModeData data = new SummerModeData(game);
                                allTheBeaches.add(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // Creamos un adaptador con la lista de datos y la actividad asociada.
                        SummerModeAdapter adapter = new SummerModeAdapter(allTheBeaches, activity);

                        // Configuramos el RecyclerView con el adaptador y un LinearLayoutManager.
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // En el siguiente método y la línea del mismo, manejamos los errores de la solicitud y mostramos un Toast con este.
                        Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        // Una vez finalizado lo anterior, agregamos la solicitud a la cola de Volley para su procesamiento.
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}

