package com.example.miclienterest;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.miclienterest.fragmentos.FragmentoDetalle;
import com.example.miclienterest.fragmentos.FragmentoLista;
import com.example.miclienterest.utils.MySingleton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private Fecha fh;
    public static Long indice;
    public static DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);

        daoSession = daoMaster.newSession();

        fh = daoSession.getFechaDao()
                .queryBuilder()
                .limit(1)
                .orderDesc(FechaDao.Properties.Id).unique();

        FragmentoLista fl = new FragmentoLista();
        fl.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long tag = (Long)v.getTag();
                indice = tag;
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentoDetalle fd = new FragmentoDetalle();
                    fd.setIndice(tag);
                    fm.beginTransaction().replace(R.id.texto_signo,fd).commit();
                }
                else{
                    Intent i  = new Intent(MainActivity.this, DetailActivity.class);
                    i.putExtra("indice",tag);
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
                    String fecha = response.getString("titulo");
                    if(fh!=null && fh.getFecha().equals(fecha)){
                        FragmentManager fr = getSupportFragmentManager();

                        fr.beginTransaction().replace(R.id.framento_signo,fl).commit();
                        return;
                    }
                    daoSession.getSignoDao().deleteAll();
                    daoSession.getFechaDao().deleteAll();

                    Iterator<String> it = horoscopo.keys();
                    while (it.hasNext()) {
                        aux = gson.fromJson(horoscopo.getJSONObject(it.next()).toString(), Signo.class);
                        signos.add(aux);
                        daoSession.getSignoDao().insert(aux);
                    }

                    fh = new Fecha();
                    fh.setFecha(fecha);
                    daoSession.getFechaDao().insert(fh);

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
                FragmentManager fr = getSupportFragmentManager();
                fr.beginTransaction().replace(R.id.framento_signo,fl).commit();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jr);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if(indice!=null)
            savedInstanceState.putLong("indice",indice);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        indice = savedInstanceState.getLong("indice");
    }
}
