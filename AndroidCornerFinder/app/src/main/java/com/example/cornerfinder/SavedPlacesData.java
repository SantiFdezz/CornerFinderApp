package com.example.cornerfinder;

import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

public class SavedPlacesData {
    private String name;
    private String imageName;
    private String description;
    private String tag;
    private String imageNameHeart;

    public SavedPlacesData(String name, String imageName, String description, String tag, String imageNameHeart){
        this.name = name;
        this.imageName = imageName;
        this.description = description;
        this.tag = tag;
        this.imageNameHeart = imageNameHeart;
    }

    public String getName() {
        return name;
    }
    public String getDescription() { return description; }
    public String getTag(){
        return tag;
    }
    public String getImageName() {
        return imageName;
    }
    public String getImageNameHeart() {
        return imageNameHeart;
    }

    // Constructor para inicializar los datos desde un objeto JSONObject.
    public SavedPlacesData(JSONObject json){
        try{
            this.name=json.getString("name");
            this.imageName=json.getString("image_name");
            this.description=json.getString("description");
            this.tag=json.getString("tag");
            this.imageNameHeart=json.getString("image_name_heart");
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
