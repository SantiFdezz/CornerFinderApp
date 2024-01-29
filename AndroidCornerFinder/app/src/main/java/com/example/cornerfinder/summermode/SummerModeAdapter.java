package com.example.cornerfinder.summermode;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cornerfinder.R;

import java.util.List;

public class SummerModeAdapter extends RecyclerView.Adapter<SummerModeViewHolder> {
    private List<SummerModeData> summer;
    private Activity activity;

    // Constructor que recibe la lista de datos del SummerModeFragment y la actividad.
    public SummerModeAdapter(List<SummerModeData> dataSet, Activity activity){
        this.summer=dataSet;
        this.activity=activity;
    }

    // Crea y devuelve un nuevo ViewHolder inflando el diseño de celda para que se visualice.
    @NonNull
    @Override
    public SummerModeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View summerView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.summer_recycler_cell, parent, false);
        return new SummerModeViewHolder(summerView);
    }

    // Asocia los datos de la posición actual a la vista del ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull SummerModeViewHolder holder, int position){
        SummerModeData dataForThisCell = summer.get(position);
        holder.showData(dataForThisCell);
    }

    // Devuelve la cantidad total de elementos en el conjunto de datos.
    @Override
    public int getItemCount(){ return summer.size(); }
}
