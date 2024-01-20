package com.example.cornerfinder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<PlacesViewHolder> {
    private List<PlacesData> allTheData;
    private Activity activity;

    public RecyclerViewAdapter(List<PlacesData> dataSet, Activity activity) {
        this.allTheData = dataSet;
        this.activity = activity;
    }

    public PlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new PlacesViewHolder(view, allTheData);
    }

    public void onBindViewHolder(PlacesViewHolder holder, int position){
        PlacesData dataInPositionToBeRendered = allTheData.get(position);
        holder.showData(dataInPositionToBeRendered, activity);
    }

    public int getItemCount(){
        return allTheData.size();
    }
}
