package com.example.cornerfinder.recommended;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cornerfinder.R;


import java.util.List;


public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedViewHolder>{
    private List<RecommendedItems> recommended;
    private Activity activity;
    public RecommendedAdapter(List<RecommendedItems> dataSet, Activity activity){
        this.recommended=dataSet;
        this.activity=activity;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View recommendView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.summer_recycler_cell, parent, false);
        return new RecommendedViewHolder(recommendView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, int position){
        RecommendedItems dataForThisCell = recommended.get(position);
        holder.showData(dataForThisCell);
    }


    @Override
    public int getItemCount(){ return recommended.size(); }
}

