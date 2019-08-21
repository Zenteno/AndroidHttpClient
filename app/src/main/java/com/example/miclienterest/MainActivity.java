package com.example.miclienterest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = MySingleton.getInstance(this).getRequestQueue();
        String url = "https://api.adderou.cl/tyaas/";

        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Signo> signos = new ArrayList();
                    Signo aux;
                    Gson gson = new Gson();
                    JSONObject horoscopo = response.getJSONObject("horoscopo");
                    Iterator<String> it = horoscopo.keys();
                    while(it.hasNext()){
                        aux = gson.fromJson(horoscopo.getJSONObject(it.next()).toString(),Signo.class);
                        signos.add(aux);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        queue.add(jr);

    }
}
