package com.example.cornerfinder;

import org.json.JSONException;
import org.json.JSONObject;

public class PlacesData {
    private String name;
    private String imageUrl;
    private String description;
    private String preference;

    public PlacesData(String name, String imageUrl, String description, String preference){
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.preference = preference;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String description() {
        return description;
    }

    public String preference(){
        return preference;
    }

    public String getDescription() { return description; }

    // Constructor para inicializar los datos desde un objeto JSONObject.
    public PlacesData(JSONObject json){
        try{
            this.name=json.getString("name");
            this.imageUrl=json.getString("image_url");
            this.description=json.getString("description");
            this.preference=json.getString("preference");
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
