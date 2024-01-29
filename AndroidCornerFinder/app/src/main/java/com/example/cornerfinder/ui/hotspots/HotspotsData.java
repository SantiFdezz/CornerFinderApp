package com.example.cornerfinder.ui.hotspots;

import org.json.JSONException;
import org.json.JSONObject;

public class HotspotsData {

    private String placeName;
    private String descripcion;
    private String etiqueta;
    private String location;
    private String image_url;

    public String getPlaceName() { return placeName; }

    public String getDescripcion() { return descripcion; }

    public String getEtiqueta() { return etiqueta; }

    public String getLocation() { return location; }

    public String getImage_url() { return image_url; }

    public HotspotsData(String placeName, String descripcion, String location, String etiqueta,String image_url){
        this.placeName=placeName;
        this.descripcion=descripcion;
        this.etiqueta=etiqueta;
        this.location=location;
        this.image_url=image_url;
    }

    public HotspotsData(JSONObject json){
        try{
            this.placeName = json.getString("placeName");
            this.descripcion = json.getString("descripcion");
            this.etiqueta = json.getString("etiqueta");
            this.location = json.getString("location");
            this.image_url = json.getString("image_url");
        }catch (JSONException e){ e.printStackTrace(); }
    }
}
