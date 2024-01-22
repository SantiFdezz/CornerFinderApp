package com.example.cornerfinder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SavedPlacesViewAdapter extends RecyclerView.Adapter<SavedPlacesViewHolder> {
    private List<SavedPlacesData> allTheData;
    private Activity activity;

    public SavedPlacesViewAdapter(List<SavedPlacesData> dataSet, Activity activity) {
        this.allTheData = dataSet;
        this.activity = activity;
    }

    public SavedPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_places_item, parent, false);
        return new SavedPlacesViewHolder(view, allTheData);
    }

    public void onBindViewHolder(SavedPlacesViewHolder holder, int position){
        SavedPlacesData dataInPositionToBeRendered = allTheData.get(position);
        holder.showData(dataInPositionToBeRendered, activity);
    }

    public int getItemCount(){
        return allTheData.size();
    }
}
