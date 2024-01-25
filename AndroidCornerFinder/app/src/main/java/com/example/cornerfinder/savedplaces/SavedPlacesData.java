package com.example.cornerfinder.savedplaces;

import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

public class SavedPlacesData {
    private String place_name;
    private String description;
    private String tag;
    private String location;
    private String image_url;

    public String getPlaceName() {
        return place_name;
    }
    public String getDescription() { return description; }
    public String getTag(){
        return tag;
    }
    public String getLocation() {
        return location;
    }
    public String getImageUrl() {
        return image_url;
    }

    public SavedPlacesData(String name, String imageName, String description, String tag, String imageNameHeart){
        this.place_name = place_name;
        this.description = description;
        this.tag = tag;
        this.location = location;
        this.image_url = image_url;
    }

    public SavedPlacesData(JSONObject json){
        try{
            this.place_name=json.getString("name");
            this.description=json.getString("description");
            this.tag=json.getString("tag");
            this.location = json.getString("location");
            this.image_url = json.getString("image_url");
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
