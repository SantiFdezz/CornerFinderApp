package com.example.cornerfinder;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SavedPlacesViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewName;
    private TextView textViewDescription;
    private TextView textViewTag;
    private ImageView imageView;
    private ImageView imageViewHeart;
    private Button button;

    public SavedPlacesViewHolder(@NonNull View itemView, List<SavedPlacesData> allTheData) {
        super(itemView);
        textViewName = (TextView) itemView.findViewById(R.id.name);
        textViewDescription = (TextView) itemView.findViewById(R.id.descripcion);
        textViewTag = (TextView) itemView.findViewById(R.id.tag);
        imageView = (ImageView) itemView.findViewById(R.id.image_view);
        imageViewHeart = (ImageView) itemView.findViewById(R.id.heart);
        button = (Button) itemView.findViewById(R.id.button);
    }

    public void showData(SavedPlacesData data, Activity activity){
        textViewName.setText(data.getName());
        textViewDescription.setText(data.getDescription());
        textViewTag.setText(data.getTag());


        int idImageName = activity.getResources().getIdentifier(data.getImageName(), "drawable", activity.getPackageName());
        int idImageNameHeart = activity.getResources().getIdentifier(data.getImageNameHeart(), "drawable", activity.getPackageName());

        Glide.with(itemView.getContext())
                .load(idImageName)
                .into(imageView);

        Glide.with(itemView.getContext())
                .load(idImageNameHeart)
                .into(imageViewHeart);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acciones a realizar cuando se hace clic en el botón
                // Puedes acceder a 'data' aquí para realizar acciones basadas en los datos específicos de ese elemento
            }
        });
    }
}
