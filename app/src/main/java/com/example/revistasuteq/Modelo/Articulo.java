package com.example.revistasuteq.Modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Articulo {

    String titulo;

    public Articulo(String titulo, String autores, String urlPdf, String seccion, String doi) {
        this.titulo = titulo;
        this.autores = autores;
        this.urlPdf = urlPdf;
        this.seccion = seccion;
        this.doi = doi;
    }

    String autores;
    String urlPdf;
    String id;
    String seccion;
    String doi;



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
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

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }


    public Articulo(JSONObject a) throws JSONException {

        titulo = a.getString("title").toString() ;
        JSONArray JSONlistaAutores = new JSONArray(a.getString("authors"));
        String lstAutores="";
        for(int i=0; i< JSONlistaAutores.length();i++){
            JSONObject autor=  JSONlistaAutores.getJSONObject(i);
            lstAutores = lstAutores + ", " + autor.getString("nombres").toString();
        }
        autores = lstAutores.substring(2);
        seccion = a.getString("section").toString() ;
        doi = a.getString("doi").toString() ;
        JSONArray JSONlistaGaleys = new JSONArray(a.getString("galeys"));
        List<String> urlGaley = new ArrayList<String>();
        for(int i=0; i< JSONlistaGaleys.length();i++){
            JSONObject galey=  JSONlistaGaleys.getJSONObject(i);
            urlGaley.add(galey.getString("UrlViewGalley").toString());
        }
        urlPdf = urlGaley.get(0);
        id = a.getString("publication_id").toString() ;
    }

    public static ArrayList<Articulo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Articulo> articulo = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            articulo.add(new Articulo(datos.getJSONObject(i)));
        }
        return articulo;
    }

}
