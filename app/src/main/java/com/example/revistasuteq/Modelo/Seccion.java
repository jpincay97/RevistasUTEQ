package com.example.revistasuteq.Modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Seccion {

    private String id, seccion;

    public Seccion(String id, String seccion) {
        this.id = id;
        this.seccion = seccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Seccion(JSONObject a) throws JSONException {

        id = a.getString("section_id").toString() ;
        seccion = a.getString("section").toString() ;
    }

    public static ArrayList<Seccion> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Seccion> seccion = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            seccion.add(new Seccion(datos.getJSONObject(i)));
        }
        return seccion;
    }
}
