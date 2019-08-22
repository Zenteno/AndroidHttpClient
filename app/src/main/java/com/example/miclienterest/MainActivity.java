package com.example.miclienterest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
        final TextView amor = findViewById(R.id.amor);
        final TextView salud = findViewById(R.id.salud);
        final TextView dinero = findViewById(R.id.dinero);

        RequestQueue queue = MySingleton.getInstance(this).getRequestQueue();
        String url = "https://api.adderou.cl/tyaas/";
        final Spinner spinner = findViewById(R.id.spinner);
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
                    while (it.hasNext()) {
                        aux = gson.fromJson(horoscopo.getJSONObject(it.next()).toString(), Signo.class);
                        signos.add(aux);
                    }
                    ArrayAdapter<Signo> adapter = new ArrayAdapter<Signo>(getApplicationContext(), android.R.layout.simple_spinner_item, signos);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Signo s = (Signo) spinner.getItemAtPosition(position);
                            amor.setText(s.getAmor());
                            salud.setText(s.getSalud());
                            dinero.setText(s.getDinero());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
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
        queue.add(jr);

    }
}
