package com.example.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.revistasuteq.Adaptador.AdaptadorRevista;
import com.example.revistasuteq.Modelo.Revista;
import com.example.revistasuteq.WebService.Asynchtask;
import com.example.revistasuteq.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    RecyclerView recyclerRevistas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleSSLHandshake();

        recyclerRevistas = (RecyclerView) findViewById(R.id.rvRevistas);
        recyclerRevistas.setLayoutManager(new LinearLayoutManager(this));

        Map<String,String> datos = new HashMap<String,String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/journals.php", datos,
                MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<Revista> listaRevistas = new ArrayList<Revista> ();
        try {
            JSONArray JSONlista =  new JSONArray(result);
            listaRevistas = Revista.JsonObjectsBuild(JSONlista);
            AdaptadorRevista adapator = new AdaptadorRevista(listaRevistas,this);
            recyclerRevistas.setAdapter(adapator);

            final ArrayList<Revista> finalListaRevistas = listaRevistas;
            adapator.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int opcion = recyclerRevistas.getChildAdapterPosition(view);
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    Bundle b = new Bundle();
                    b.putString("id_revista", finalListaRevistas.get(opcion).getId().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                }
            });

        }catch (JSONException e)
        {
            Toast.makeText(this.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }
    }

    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
}
