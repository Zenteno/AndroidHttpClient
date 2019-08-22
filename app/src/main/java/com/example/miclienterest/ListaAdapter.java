package com.example.miclienterest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.MyViewHolder> {

    private List<Signo> items;
    private Context context;

    public ListaAdapter(Context context, List<Signo> items) {
        this.context = context;
        this.items= items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder itemsViewHolder, int i) {

        itemsViewHolder.vTitle.setText(items.get(i).getNombre());
        itemsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsViewHolder.ep.toggle(true);
                //imprime el padre, en este caso el recyclerview
                System.out.println(itemsViewHolder.itemView.getParent());
            }
        });

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_lista, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        protected TextView vTitle;
        protected ExpandableLayout ep;

        public MyViewHolder(View v) {
            super(v);
            ep = v.findViewById(R.id.expandable_layout);
            vTitle = v.findViewById(R.id.title);
        }
    }
}