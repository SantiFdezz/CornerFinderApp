package com.example.cornerfinder.recommended;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cornerfinder.R;
import com.example.cornerfinder.summermode.SummerModeData;
import com.example.cornerfinder.summermode.Util;

public class RecommendedViewHolder extends RecyclerView.ViewHolder {
    private TextView placeName;
    private TextView etiqueta;
    private TextView descripcion;
    private ImageView imageView;
    private Button location;


    public RecommendedViewHolder(@NonNull View ivi){
        super(ivi);
        placeName = ivi.findViewById(R.id.placeName);
        etiqueta = ivi.findViewById(R.id.etiqueta);
        descripcion = ivi.findViewById(R.id.descripcion);
        imageView = ivi.findViewById(R.id.image_view);
        location = ivi.findViewById(R.id.location);
    }


    public void showData(RecommendedItems items){
        placeName.setText(items.getPlaceName());
        descripcion.setText(items.getDescripcion());
        etiqueta.setText(items.getEtiqueta());
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
