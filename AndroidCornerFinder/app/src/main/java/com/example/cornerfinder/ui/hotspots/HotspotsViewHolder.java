package com.example.cornerfinder.ui.hotspots;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cornerfinder.R;
import com.example.cornerfinder.ui.hotspots.HotspotsData;
import com.example.cornerfinder.ui.hotspots.Util;

public class HotspotsViewHolder extends RecyclerView.ViewHolder {
    private TextView placeName;
    private TextView etiqueta;
    private TextView descripcion;
    private ImageView imageView;
    private Button location;

    // Constructor que inicializa las vistas utilizando el diseño de celda:
    public HotspotsViewHolder(@NonNull View ivi){
        super(ivi);
        placeName = ivi.findViewById(R.id.placeName);
        etiqueta = ivi.findViewById(R.id.etiqueta);
        descripcion = ivi.findViewById(R.id.descripcion);
        imageView = ivi.findViewById(R.id.image_view);
        location = ivi.findViewById(R.id.location);
    }

    // Método para mostrar los datos en las vistas correspondientes:
    public void showData(HotspotsData hotspotsData){
        placeName.setText(hotspotsData.getPlaceName());
        descripcion.setText(hotspotsData.getDescripcion());
        etiqueta.setText(hotspotsData.getEtiqueta());
        Util.downloadBitmapToImageView(hotspotsData.getImage_url(), this.imageView);

        // Comprobar que funciona bien el botón locatin y obtiene los datos.
        location.setOnClickListener(v -> Log.i("Funciona el botón. Location: ", hotspotsData.getLocation()));
    }
}
