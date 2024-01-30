package com.example.cornerfinder.ui.hotspots;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cornerfinder.R;
import com.example.cornerfinder.ui.hotspots.HotspotsData;
import com.example.cornerfinder.ui.hotspots.HotspotsViewHolder;

import java.util.List;

public class HotspotsAdapter extends RecyclerView.Adapter<HotspotsViewHolder> {
    private List<HotspotsData> hotspots;
    private Activity activity;

    // Constructor que recibe la lista de datos del SummerModeFragment y la actividad.
    public HotspotsAdapter(List<HotspotsData> dataSet, Activity activity){
        this.hotspots=dataSet;
        this.activity=activity;
    }

    // Crea y devuelve un nuevo ViewHolder inflando el diseño de celda para que se visualice.
    @NonNull
    @Override
    public HotspotsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View hotspotsView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_hotspots, parent, false);
        return new HotspotsViewHolder(hotspotsView);
    }

    // Asocia los datos de la posición actual a la vista del ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull HotspotsViewHolder holder, int position){
        HotspotsData dataForThisCell = hotspots.get(position);
        holder.showData(dataForThisCell);
    }

    // Devuelve la cantidad total de elementos en el conjunto de datos.
    @Override
    public int getItemCount(){ return hotspots.size(); }
}
