package com.example.cornerfinder.savedplaces;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cornerfinder.R;

public class SavedPlacesAdapter extends RecyclerView.Adapter<SavedPlacesViewHolder> {
    private List<SavedPlacesData> saved;
    private Activity activity;

    public SavedPlacesAdapter(List<SavedPlacesData> dataSet, Activity activity) {
        this.saved = dataSet;
        this.activity = activity;
    }

    @NonNull
    @Override
    public SavedPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View savedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_saved_places, parent, false);
        return new SavedPlacesViewHolder(savedView);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedPlacesViewHolder holder, int position){
        SavedPlacesData dataForThisCell = saved.get(position);
        holder.showData(dataForThisCell);
    }

    @Override
    public int getItemCount(){
        return saved.size();
    }
}
