package com.example.cornerfinder.savedplaces;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cornerfinder.R;
import com.example.cornerfinder.savedplaces.SavedPlacesData;
import com.example.cornerfinder.summermode.Util;

import java.util.List;

public class SavedPlacesViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewName;
    private TextView textViewDescription;
    private TextView textViewTag;
    private ImageView imageView;
    private Button location;

    public SavedPlacesViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = (TextView) itemView.findViewById(R.id.name);
        textViewDescription = (TextView) itemView.findViewById(R.id.descripcion);
        textViewTag = (TextView) itemView.findViewById(R.id.tag);
        imageView = (ImageView) itemView.findViewById(R.id.image_view);
        location = (Button) itemView.findViewById(R.id.location);
    }

    public void showData(SavedPlacesData savedData){
        textViewName.setText(savedData.getPlaceName());
        textViewDescription.setText(savedData.getDescription());
        textViewTag.setText(savedData.getTag());
        Util.downloadBitmapToImageView(savedData.getImageUrl(), this.imageView);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            // Comprobar que funciona bien el botón locatin y obtiene los datos.
            public void onClick(View v) {
                System.out.println("Funciona el botón. Location: "+ savedData.getLocation());
            }
        });
    }
}
