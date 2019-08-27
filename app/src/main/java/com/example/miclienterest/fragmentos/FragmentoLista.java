package com.example.miclienterest.fragmentos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miclienterest.DaoSession;
import com.example.miclienterest.MainActivity;
import com.example.miclienterest.R;
import com.example.miclienterest.Signo;
import com.example.miclienterest.adapters.ListaAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentoLista extends Fragment {

    @BindView(R.id.lista) RecyclerView rv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragmento_lista,container,false);
        ButterKnife.bind(this,v);
        rv.setHasFixedSize(true);
        LinearLayoutManager llmanager = new LinearLayoutManager(getActivity());
        llmanager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llmanager);

        DaoSession ds = MainActivity.daoSession;
        ArrayList<Signo> signos = new ArrayList(ds.getSignoDao().loadAll());
        ListaAdapter myAdapter = new ListaAdapter(signos);
        myAdapter.setClickListener(mClickListener);
        rv.setAdapter(myAdapter);
        return v;
    }

    public void setClickListener(View.OnClickListener callback) {
        mClickListener = callback;
    }
    private View.OnClickListener mClickListener;
}
