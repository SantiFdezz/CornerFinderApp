package com.example.cornerfinder;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SavedPlacesViewHolder extends RecyclerView.ViewHolder {
    private final TextView textView1;
    private final TextView textView2;
    private final ImageView imageView;

    public SavedPlacesViewHolder(@NonNull View itemView, List<SavedPlacesData> allTheData) {
        super(itemView);
        textView1 = (TextView) itemView.findViewById(R.id.text_view1);
        textView2 = (TextView) itemView.findViewById(R.id.text_view2);
        imageView = (ImageView) itemView.findViewById(R.id.image_view);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void showData(SavedPlacesData data, Activity activity){
        // Mostrar los datos.
        textView1.setText(data.getName());
        Glide.with(itemView.getContext()) // Utilizar Glide para cargar la imagen desde la URL.
                .load(data.getImageUrl()) // Cargar la imagen.
                .into(imageView); // Colocar la imagen.
    }
}
