package com.example.tarealistviewprendas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Prendas {

    private String titulo;
    private String precio;
    private String descripcion;
    private String categoria;
    private String urlimagen;




    public Prendas(JSONObject a) throws JSONException {

        titulo = a.getString("title").toString() ;
        precio = a.getString("price").toString() ;
        descripcion = a.getString("description").toString() ;
        categoria = a.getString("category").toString() ;
        urlimagen = a.getString("image").toString() ;
    }
    public static ArrayList<Prendas> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Prendas> prendas = new ArrayList<>();
        for (int i = 0; i < datos.length() && i<20; i++) {
            prendas.add(new Prendas(datos.getJSONObject(i)));
        }
        return prendas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }
}
