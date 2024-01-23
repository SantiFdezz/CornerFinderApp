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

    public SummerModeAdapter(List<SummerModeData> dataSet, Activity activity){
        this.summer=dataSet;
        this.activity=activity;
    }


    @NonNull
    @Override
    public SummerModeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View summerView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.summer_recycler_cell, parent, false);
        return new SummerModeViewHolder(summerView);
    }

    @Override
    public void onBindViewHolder(@NonNull SummerModeViewHolder holder, int position){
        SummerModeData dataForThisCell = summer.get(position);
        holder.showData(dataForThisCell);
    }


    @Override
    public int getItemCount(){ return summer.size(); }
}
