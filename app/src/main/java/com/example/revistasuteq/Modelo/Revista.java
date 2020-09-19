package com.example.revistasuteq.Modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Revista {

    private String id;
    private String nombre;
    private String descripcion;
    private String imagen;

    public Revista(){

    }

    public Revista(String nombre, String descripcion, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Revista(JSONObject a) throws JSONException {
        id = a.getString("journal_id").toString();
        nombre =  a.getString("name").toString() ;
        descripcion =  a.getString("description").toString() ;
        imagen = a.getString("portada").toString() ;
    }

    public static ArrayList<Revista> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Revista> revistas = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            revistas.add(new Revista(datos.getJSONObject(i)));
        }
        return revistas;
    }

}
