package com.example.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.revistasuteq.Adaptador.AdaptadorArticulo;
import com.example.revistasuteq.Adaptador.AdaptadorSeccion;
import com.example.revistasuteq.Modelo.Articulo;
import com.example.revistasuteq.Modelo.Seccion;
import com.example.revistasuteq.WebService.Asynchtask;
import com.example.revistasuteq.WebService.WebService;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity implements Asynchtask {

    RecyclerView recyclerArticulos;
    ArrayList<Articulo> listaArticulos;
    Bundle bundle;
    int opcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        MainActivity.handleSSLHandshake();

        recyclerArticulos = (RecyclerView) findViewById(R.id.rvArticulos);
        recyclerArticulos.setLayoutManager(new LinearLayoutManager(this));
        bundle = this.getIntent().getExtras();
        Map<String,String> datos = new HashMap<String,String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/pubs.php?i_id="
                +bundle.getString("id_edicion"), datos, MainActivity3.this, MainActivity3.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        listaArticulos = new ArrayList<Articulo> ();
        try {
            JSONArray JSONlista =  new JSONArray(result);
            listaArticulos = Articulo.JsonObjectsBuild(JSONlista);
            AdaptadorArticulo adapator = new AdaptadorArticulo(listaArticulos,this);
            recyclerArticulos.setAdapter(adapator);

            final ArrayList<Articulo> finalListaArticulos = listaArticulos;
            adapator.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    opcion = recyclerArticulos.getChildAdapterPosition(view);
                }
            });

        }catch (JSONException e)
        {
            Toast.makeText(this.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }
    }

    public void irAURL(View view){
        Uri uri = Uri.parse(listaArticulos.get(opcion).getUrlPdf());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
