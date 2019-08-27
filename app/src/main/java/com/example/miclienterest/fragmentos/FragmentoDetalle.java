package com.example.miclienterest.fragmentos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.miclienterest.MainActivity;
import com.example.miclienterest.R;
import com.example.miclienterest.Signo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentoDetalle extends Fragment {

    private Long indice;

    @BindView(R.id.salud) TextView salud;
    @BindView(R.id.dinero) TextView dinero;
    @BindView(R.id.amor) TextView amor;
    @BindView(R.id.nombre_signo) TextView nombre;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragmento_detalle,container,false);
        ButterKnife.bind(this,v);
        if(indice==null) {
            indice = MainActivity.indice;
            //if(indice ==null) return v;
        }
        Signo s = MainActivity.daoSession
                .getSignoDao().loadByRowId(indice);
        salud.setText(s.getSalud());
        dinero.setText(s.getDinero());
        amor.setText(s.getAmor());
        nombre.setText(s.getNombre());
        return v;
    }

    public void setIndice(Long indice){
        this.indice = indice;
    }
}
