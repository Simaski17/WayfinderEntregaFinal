package com.wayfinder.rinno.wayfinderentregafinal.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;


import com.wayfinder.rinno.wayfinderentregafinal.R;
import com.wayfinder.rinno.wayfinderentregafinal.fastscroller.RecyclerViewFastScroller;
import com.wayfinder.rinno.wayfinderentregafinal.model.PopupMapa;
import com.wayfinder.rinno.wayfinderentregafinal.model.RubroOpcion;
import com.wayfinder.rinno.wayfinderentregafinal.model.Stores;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tecnova on 26-03-2017.
 */

public class TiendasViewAdapter extends RecyclerView.Adapter<TiendasViewAdapter.TiendasViewHolder> implements RecyclerViewFastScroller.BubbleTextGetter,SectionIndexer {

    private int opciones;
    private ArrayList<Stores> dataset;
    public Context context;
    TiendasViewHolder vh;



    private List<String> mDataArray;
    private ArrayList<Integer> mSectionPositions;



    public  TiendasViewAdapter(int opciones, ArrayList<Stores> dataset, Context context) {
        this.opciones = opciones;
        this.dataset = dataset;
        this.context = context;
        mDataArray = new ArrayList<>();

        for(Stores store : dataset)
        {
            mDataArray.add(store.getName());
        }
    }

    @Override
    public TiendasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_tiendas, parent, false);
        vh = new TiendasViewHolder(view);

        return vh;

    }

    @Override
    public void onBindViewHolder(TiendasViewHolder holder, final int position) {

        if (opciones == 1) {
            Log.d("Aqui","cambiando categoria");
            holder.contentDos.setVisibility(View.GONE);
            holder.contentUno.setVisibility(View.VISIBLE);
            holder.name.setText(dataset.get(position).getName());
        } else if (opciones == 2) {
            holder.contentDos.setVisibility(View.VISIBLE);
            holder.contentUno.setVisibility(View.GONE);
            holder.nameDos.setText(dataset.get(position).getName());
            holder.storeNumberDos.setText(dataset.get(position).getStoreNumber());
            holder.roadDos.setText(dataset.get(position).getRoad());
        } else {
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
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (opciones == 1) {
                    EventBus.getDefault().postSticky(new RubroOpcion(1,dataset.get(position).getCategory()));
                } else {
                    EventBus.getDefault().postSticky(new PopupMapa(dataset.get(position).getName(), dataset.get(position).getStoreNumber(), dataset.get(position).getPhone(), dataset.get(position).getNodeID(),
                            dataset.get(position).getEmail(), dataset.get(position).getWeb(), dataset.get(position).getStoreImage(), dataset.get(position).getTags()));
                }
            }
        });

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.recyclerOpcionesFragment));
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.fondoRecycler));
        }

    }

    public void clearData() {
        dataset.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }



    @Override
    public String getTextToShowInBubble(int pos) {
        if (pos < 0 || pos >= dataset.size())
            return null;

        String name = dataset.get(pos).getName();
        if (name == null || name.length() < 1)
            return null;

        return dataset.get(pos).getName().substring(0, 1);
    }

    @Override
    public Object[] getSections() {
        List<String> sections = new ArrayList<>(26);
        mSectionPositions = new ArrayList<>(26);
        for (int i = 0, size = mDataArray.size(); i < size; i++) {
            String section = StringUtils.stripAccents(String.valueOf(mDataArray.get(i).charAt(0)).toUpperCase());
            if(StringUtils.isNumeric(section))
            {
                section = "#";
            }
            if (!sections.contains(section)) {
                sections.add(section);
                mSectionPositions.add(i);
            }
        }
        return sections.toArray(new String[0]);
        }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return mSectionPositions.get(sectionIndex);
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }


    public static class TiendasViewHolder extends RecyclerView.ViewHolder {

        public Context context;
        public TextView name;
        public TextView storeNumber;
        public TextView road;
        public TextView nameDos;
        public TextView storeNumberDos;
        public TextView roadDos;
        public TextView floor;
        LinearLayout contentUno, contentDos;
        RelativeLayout relativeLayout;

        public TiendasViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            storeNumber = (TextView) itemView.findViewById(R.id.tv_storeNumber);
            road = (TextView) itemView.findViewById(R.id.tv_road);
            nameDos = (TextView) itemView.findViewById(R.id.tv_namedos);
            storeNumberDos = (TextView) itemView.findViewById(R.id.tv_storeNumberdos);
            roadDos = (TextView) itemView.findViewById(R.id.tv_roaddos);
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


}

