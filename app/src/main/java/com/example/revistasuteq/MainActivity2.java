package com.example.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.revistasuteq.Adaptador.AdaptadorEdicion;
import com.example.revistasuteq.Modelo.Edicion;
import com.example.revistasuteq.Modelo.Seccion;
import com.example.revistasuteq.WebService.Asynchtask;
import com.example.revistasuteq.WebService.WebService;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity implements Asynchtask {

    RecyclerView recyclerEdiciones;
    //private RequestQueue queue;
    private String id_edicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        MainActivity.handleSSLHandshake();
        //queue = Volley.newRequestQueue(this);
        Bundle bundle = this.getIntent().getExtras();

        recyclerEdiciones = (RecyclerView) findViewById(R.id.rvEdiciones);
        recyclerEdiciones.setLayoutManager(new LinearLayoutManager(this));

        Map<String,String> datos = new HashMap<String,String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/issues.php?j_id="
                +bundle.getString("id_revista"), datos, MainActivity2.this, MainActivity2.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<Edicion> listaEdiciones = new ArrayList<Edicion> ();
        try {
            JSONArray JSONlista =  new JSONArray(result);
            listaEdiciones = Edicion.JsonObjectsBuild(JSONlista);
            AdaptadorEdicion adapator = new AdaptadorEdicion(listaEdiciones,this);
            recyclerEdiciones.setAdapter(adapator);

            final ArrayList<Edicion> finalListaEdiciones = listaEdiciones;
            adapator.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
                    int opcion = recyclerEdiciones.getChildAdapterPosition(view);
                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                    Bundle b = new Bundle();
                    b.putString("id_edicion", finalListaEdiciones.get(opcion).getId());
                    intent.putExtras(b);
                    startActivity(intent);
                }
            });

        }catch (JSONException e)
        {
            Toast.makeText(this.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }
    }

    /*public void obtenerSecciones(String id){
        Bundle bundle = this.getIntent().getExtras();
        StringRequest request = new StringRequest(Request.Method.GET,
                "https://revistas.uteq.edu.ec/ws/pubssections.php?i_id="+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            ArrayList<Seccion> listaSecciones = new ArrayList<Seccion> ();
                            JSONArray JSONlista =  new JSONArray(response);
                            listaSecciones = Seccion.JsonObjectsBuild(JSONlista);
                            b.putString("seccion", listaSecciones.get(0).getSeccion());
                        }
                        catch (JSONException e){

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }*/

}
