package com.example.miclienterest;

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

        FragmentManager fm = getSupportFragmentManager();
        FragmentoDetalle fd = new FragmentoDetalle();
        fm.beginTransaction().replace(R.id.texto_signo,fd).commit();
    }
}
