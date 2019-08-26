package com.example.miclienterest;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.miclienterest.fragmentos.FragmentoDetalle;
import com.example.miclienterest.fragmentos.FragmentoLista;
import com.example.miclienterest.utils.ArregloSigno;
import com.example.miclienterest.utils.MySingleton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        FragmentoLista fl = new FragmentoLista();
        fl.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView rv = (RecyclerView)v.getParent();
                int pos = rv.getChildLayoutPosition(v);
                ArregloSigno.setIndice(pos);
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentoDetalle fd = new FragmentoDetalle();
                    fm.beginTransaction().replace(R.id.texto_signo,fd).commit();
                }
                else{
                    Intent i  = new Intent(MainActivity.this, DetailActivity.class);
                    startActivity(i);

                }
            }
        });
        String url = "https://api.adderou.cl/tyaas/";
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Signo> signos = new ArrayList<>();
                    Signo aux;
                    Gson gson = new Gson();
                    JSONObject horoscopo = response.getJSONObject("horoscopo");
                    Iterator<String> it = horoscopo.keys();
                    while (it.hasNext()) {
                        aux = gson.fromJson(horoscopo.getJSONObject(it.next()).toString(), Signo.class);
                        signos.add(aux);
                    }
                    ArregloSigno.setArreglo(signos);
                    FragmentManager fr = getSupportFragmentManager();

                    fr.beginTransaction().replace(R.id.framento_signo,fl).commit();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jr);

    }
}
