package com.example.miclienterest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.MyViewHolder> {

    private List<Signo> items;
    private ExpandableLayout anterior = null;
    private ExpandableLayout costado;
    private ExpandableLayout main;

    public ListaAdapter(List<Signo> items, ExpandableLayout costado, ExpandableLayout main) {
        this.costado = costado;
        this.items= items;
        this.main = main;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder itemsViewHolder, int i) {

        itemsViewHolder.vTitle.setText(items.get(i).getNombre());

        itemsViewHolder.vTexto.setText(items.get(i).getAmor());
        Glide.with(itemsViewHolder.itemView.getContext())
                .load("https://bucket1.glanacion.com/anexos/fotos/12/3082812w1033.jpg")
                .into(itemsViewHolder.img);

        itemsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(anterior!=null && anterior.isExpanded())
                    anterior.collapse();
                if(anterior!=null && anterior.equals(itemsViewHolder.ep)){
                    anterior = null;
                    return;
                }
                anterior = itemsViewHolder.ep;
                anterior.toggle(true);
            }
        });
        itemsViewHolder.vTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.toggle(true);
                costado.toggle(true);
            }
        });

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_lista, viewGroup, false);
        MyViewHolder mv = new MyViewHolder(itemView);
        return mv;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.expandable_layout) ExpandableLayout ep;
        @BindView(R.id.imagen) ImageView img;
        @BindView(R.id.title) TextView vTitle;
        @BindView(R.id.texto) TextView vTexto;

        public MyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }
    }
}