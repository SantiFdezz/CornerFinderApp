package com.example.cornerfinder.recommended;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cornerfinder.R;
import com.example.cornerfinder.summermode.Util;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView place_name;
    private TextView tag;
    private TextView description;
    private ImageView imageView;
    private Button location;


    public RecyclerViewHolder(@NonNull View ivi){
        super(ivi);
        place_name = ivi.findViewById(R.id.place_name);
        tag = ivi.findViewById(R.id.tag);
        description = ivi.findViewById(R.id.description);
        imageView = ivi.findViewById(R.id.image_view);
        location = ivi.findViewById(R.id.location);
    }


    public void showData(RecyclerItems items){
        place_name.setText(items.getPlace_Name());
        description.setText(items.getDescription());
        tag.setText(items.getTag());
        Util.downloadBitmapToImageView(items.getImage_url(), this.imageView);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            // Comprobar que funciona bien el botón locatin y obtiene los datos.
            public void onClick(View v) {
                System.out.println("Funciona el botón. Location: "+items.getLocation());
            }
        });
    }
}
