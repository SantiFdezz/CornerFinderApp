package com.example.cornerfinder.summermode;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cornerfinder.R;

public class SummerModeViewHolder extends RecyclerView.ViewHolder {
    private TextView placeName;
    private TextView etiqueta;
    private TextView descripcion;
    private ImageView imageView;
    private Button location;

    // Constructor que inicializa las vistas utilizando el diseño de celda:
    public SummerModeViewHolder(@NonNull View ivi){
        super(ivi);
        placeName = ivi.findViewById(R.id.placeName);
        etiqueta = ivi.findViewById(R.id.etiqueta);
        descripcion = ivi.findViewById(R.id.descripcion);
        imageView = ivi.findViewById(R.id.image_view);
        location = ivi.findViewById(R.id.location);
    }

    // Método para mostrar los datos en las vistas correspondientes:
    public void showData(SummerModeData summerData){
        placeName.setText(summerData.getPlaceName());
        descripcion.setText(summerData.getDescripcion());
        etiqueta.setText(summerData.getEtiqueta());
        Util.downloadBitmapToImageView(summerData.getImage_url(), this.imageView);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            // Comprobar que funciona bien el botón locatin y obtiene los datos.
            public void onClick(View v) {
                System.out.println("Funciona el botón. Location: "+summerData.getLocation());
            }
        });
    }
}
