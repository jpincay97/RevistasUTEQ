package com.example.revistasuteq.Modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Edicion {

    private String imagen, volumen, numero, anio, fecha, id;

    public Edicion(String imagen, String volumen, String numero, String anio, String fecha) {
        this.imagen = imagen;
        this.volumen = volumen;
        this.numero = numero;
        this.anio = anio;
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Edicion(JSONObject a) throws JSONException {
        imagen = a.getString("cover").toString() ;
        volumen = a.getString("volume").toString();
        numero =  a.getString("number").toString() ;
        anio =  a.getString("year").toString() ;
        fecha =  a.getString("date_published").toString() ;
        id = a.getString("issue_id").toString() ;
    }

    public static ArrayList<Edicion> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Edicion> edicion = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            edicion.add(new Edicion(datos.getJSONObject(i)));
        }
        return edicion;
    }
}
