package com.wayfinder.rinno.wayfinderentregafinal.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.wayfinder.rinno.wayfinderentregafinal.FirebaseApplication;
import com.wayfinder.rinno.wayfinderentregafinal.R;
import com.wayfinder.rinno.wayfinderentregafinal.fastscroller.RecyclerViewFastScroller;
import com.wayfinder.rinno.wayfinderentregafinal.model.Busqueda;
import com.wayfinder.rinno.wayfinderentregafinal.model.PopupMapa;
import com.wayfinder.rinno.wayfinderentregafinal.model.Stores;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tecnova on 11-04-2017.
 */

public class ComidaViewAdapter extends RecyclerView.Adapter<ComidaViewAdapter.ViewHolder> implements Filterable, RecyclerViewFastScroller.BubbleTextGetter {

    private ArrayList<Stores> dataset;
    private Context context;
    RelativeLayout relativeLayout;
    FirebaseApplication app;
    ViewHolder vh;

    public CustomFilter mFilter;
    public ArrayList<Stores> dictionaryWords;
    public ArrayList<Stores> filteredList;

    public ComidaViewAdapter(ArrayList<Stores> dataset, Context context) {
        this.context = context;
        this.dataset = dataset;
        app = (FirebaseApplication) context.getApplicationContext();
        mFilter = new CustomFilter(ComidaViewAdapter.this);
        dictionaryWords = app.messages;
        filteredList = new ArrayList<Stores>();
        filteredList.addAll(dictionaryWords);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_tiendas, parent, false);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.rlPisoNumero);*/

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_tiendas, parent, false);
        vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /*Stores p = dataset.get(position);
        holder.name.setText(p.getName());
        holder.storeNumber.setText(p.getStoreNumber());
        holder.road.setText(p.getRoad());
        holder.floor.setText("P" + p.getFloor());

        if (dataset.get(position).getFloor() == 1) {
            relativeLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ovalo_borde_azul));
        } else if (dataset.get(position).getFloor() == 2) {
            relativeLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ovalo_borde_naranja));
        } else if (dataset.get(position).getFloor() == 3) {
            relativeLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ovalo_borde_verde));
        }*/


            holder.contentDos.setVisibility(View.GONE);
            holder.contentUno.setVisibility(View.VISIBLE);
            holder.name.setText(dataset.get(position).getName());
            holder.storeNumber.setText(dataset.get(position).getStoreNumber());
            holder.road.setText(dataset.get(position).getRoad());
            holder.floor.setText("P" + dataset.get(position).getFloor());
            if (holder.floor.getText().equals("P1")) {
                holder.relativeLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_piso_uno));
            } else if (holder.floor.getText().equals("P2")) {
                holder.relativeLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_piso_dos));
            } else if (holder.floor.getText().equals("P3")) {
                holder.relativeLayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_piso_tres));
            }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new PopupMapa(dataset.get(position).getName(),dataset.get(position).getStoreNumber(),dataset.get(position).getPhone(), dataset.get(position).getNodeID(),
                        dataset.get(position).getEmail(), dataset.get(position).getWeb(), dataset.get(position).getStoreImage(), dataset.get(position).getTags()));
                /*Intent intent = new Intent(context, TiendaMapaActivity.class);
                intent.putExtra("nombreTienda", dataset.get(position).getName());
                intent.putExtra("local", dataset.get(position).getStoreNumber());
                intent.putExtra("telefono", dataset.get(position).getPhone());
                intent.putExtra("nodeId", dataset.get(position).getNodeID());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*/
            }
        });

        if (position % 2 == 0) {
            //holder.itemView.setBackgroundColor(Color.GREEN);
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.recyclerOpcionesFragment));
        } else {
            //holder.itemView.setBackgroundColor(Color.RED);
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.fondoRecycler));
        }

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Stores> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    @Override
    public String getTextToShowInBubble(int pos) {
        Log.e("TAG","WEWEWEWEWEWE:");
        if (pos < 0 || pos >= dataset.size())
            return null;

        String name = dataset.get(pos).getName();
        if (name == null || name.length() < 1)
            return null;

        return dataset.get(pos).getName().substring(0, 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView storeNumber;
        private TextView road;
        TextView floor;
        LinearLayout contentUno, contentDos;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.tv_name);
            storeNumber = (TextView) itemView.findViewById(R.id.tv_storeNumber);
            road = (TextView) itemView.findViewById(R.id.tv_road);
            floor = (TextView) itemView.findViewById(R.id.tv_numeroPiso);
            contentUno = (LinearLayout) itemView.findViewById(R.id.contentUno);
            contentDos = (LinearLayout) itemView.findViewById(R.id.contentDos);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rlPisoNumero);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }
    }


    public class CustomFilter extends Filter {
        private ComidaViewAdapter mAdapter;

        private CustomFilter(ComidaViewAdapter mAdapter) {
            super();
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                filteredList.addAll(dictionaryWords);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Stores mWords : dictionaryWords) {
                    /*if (mWords.getName().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(mWords);
                    }*/

                    String cadena = (mWords.getName()+mWords.getStoreNumber()+mWords.getTags()).toLowerCase();
                    if(cadena.contains(filterPattern.toLowerCase())){
                        filteredList.add(mWords);
                    }
                }
            }
            System.out.println("Count Number " + filteredList.size());
            results.values = filteredList;
            results.count = filteredList.size();
            EventBus.getDefault().postSticky(new Busqueda(2, filteredList));
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            System.out.println("Count Number 2 " + ((List<Stores>) results.values).size());
            this.mAdapter.notifyDataSetChanged();
        }
    }

}