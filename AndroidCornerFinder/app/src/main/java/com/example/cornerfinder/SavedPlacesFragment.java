package com.example.cornerfinder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class SavedPlacesFragment extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SavedPlacesViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_places);

        // Inicializar el RecyclerView y el adaptador
        recyclerView = findViewById(R.id.saved_places_recycler_view);
        //adapter = new SavedPlacesViewAdapter(, this);

        // Configurar el RecyclerView con el adaptador
        recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
