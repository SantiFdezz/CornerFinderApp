package com.example.cornerfinder.recommended;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cornerfinder.R;


import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
    private List<RecyclerItems> dataset;
    private Fragment fragment;

    public RecyclerAdapter(List<RecyclerItems> dataSet, Fragment fragment){
        this.dataset=dataSet;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View recommendView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_cell, parent, false);
        return new RecyclerViewHolder(recommendView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position){
        RecyclerItems dataForThisCell = dataset.get(position);
        holder.showData(dataForThisCell);
    }

    @Override
    public int getItemCount(){ return dataset.size(); }
}

