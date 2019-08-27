package com.example.miclienterest;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.miclienterest.fragmentos.FragmentoDetalle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_detalle);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        Intent i = getIntent();
        Long indice = i.getLongExtra("indice",1);
        FragmentManager fm = getSupportFragmentManager();
        FragmentoDetalle fd = new FragmentoDetalle();
        fd.setIndice(indice);
        fm.beginTransaction().replace(R.id.texto_signo,fd).commit();
    }
}
