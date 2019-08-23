package com.example.miclienterest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.MyViewHolder> {

    private List<Signo> items;
    private Context ctx;
    private ExpandableLayout anterior = null;

    public ListaAdapter(List<Signo> items) {
        this.items= items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder itemsViewHolder, int i) {

        itemsViewHolder.vTitle.setText(items.get(i).getNombre());

        itemsViewHolder.vTexto.setText(items.get(i).getAmor());
        itemsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(anterior!=null && anterior.isExpanded())
                anterior.collapse();
            anterior = itemsViewHolder.ep;
            anterior.toggle();
            }
        });

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_lista, viewGroup, false);
        MyViewHolder mv = new MyViewHolder(itemView);

        Glide.with(mv.itemView)
                .load("https://bucket1.glanacion.com/anexos/fotos/12/3082812w1033.jpg")
                .into(mv.img);
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