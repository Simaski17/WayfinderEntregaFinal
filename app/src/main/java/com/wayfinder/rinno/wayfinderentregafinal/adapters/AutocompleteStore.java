package com.wayfinder.rinno.wayfinderentregafinal.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.wayfinder.rinno.wayfinderentregafinal.R;
import com.wayfinder.rinno.wayfinderentregafinal.RGlobal;
import com.wayfinder.rinno.wayfinderentregafinal.model.PopupMapa;
import com.wayfinder.rinno.wayfinderentregafinal.model.RubroOpcion;
import com.wayfinder.rinno.wayfinderentregafinal.model.Stores;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


public class AutocompleteStore extends ArrayAdapter<Stores> {

    private ArrayList<Stores> listaTiendas = new ArrayList<>();
    private ArrayList<Stores> listaTiendasSugerencia = new ArrayList<>();

    private final int mLayoutResourceId;
    private final Context mContext;
    private int opciones;


    public AutocompleteStore(@NonNull Context context, @LayoutRes int resource, @NonNull List<Stores> objects, int opciones) {
        super(context, resource, objects);
        this.mLayoutResourceId = resource;
        this.mContext = context;

    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
            }
            Stores tienda = getItem(position);

            TextView name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView storeNumber = (TextView) convertView.findViewById(R.id.tv_storeNumber);
            TextView road = (TextView) convertView.findViewById(R.id.tv_road);
            TextView nameDos = (TextView) convertView.findViewById(R.id.tv_namedos);
            TextView storeNumberDos = (TextView) convertView.findViewById(R.id.tv_storeNumberdos);
            TextView roadDos = (TextView) convertView.findViewById(R.id.tv_roaddos);
            TextView floor = (TextView) convertView.findViewById(R.id.tv_numeroPiso);
            LinearLayout contentUno = (LinearLayout) convertView.findViewById(R.id.contentUno);
            LinearLayout contentDos = (LinearLayout) convertView.findViewById(R.id.contentDos);
            RelativeLayout relativeLayout = (RelativeLayout) convertView.findViewById(R.id.rlPisoNumero);


            if (opciones == 1) {

                contentDos.setVisibility(View.GONE);
                contentUno.setVisibility(View.VISIBLE);
                name.setText(listaTiendas.get(position).getName());
            } else if (opciones == 2) {
                contentDos.setVisibility(View.VISIBLE);
                contentUno.setVisibility(View.GONE);
                nameDos.setText(listaTiendas.get(position).getName());
                storeNumberDos.setText(listaTiendas.get(position).getStoreNumber());
                roadDos.setText(listaTiendas.get(position).getRoad());
            } else {
                contentDos.setVisibility(View.GONE);
                contentUno.setVisibility(View.VISIBLE);
                name.setText(listaTiendas.get(position).getName());
                storeNumber.setText(listaTiendas.get(position).getStoreNumber());
                road.setText(listaTiendas.get(position).getRoad());
                floor.setText("P" + listaTiendas.get(position).getFloor());
                if (floor.getText().equals("P1")) {
                    relativeLayout.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_piso_uno));
                } else if (floor.getText().equals("P2")) {
                    relativeLayout.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_piso_dos));
                } else if (floor.getText().equals("P3")) {
                    relativeLayout.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_piso_tres));
                }
            }


            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (opciones == 1) {
                        EventBus.getDefault().postSticky(new RubroOpcion(1,listaTiendas.get(position).getName()));
                    } else {
                        EventBus.getDefault().postSticky(new PopupMapa(listaTiendas.get(position).getName(), listaTiendas.get(position).getStoreNumber(), listaTiendas.get(position).getPhone(), listaTiendas.get(position).getNodeID(),
                                listaTiendas.get(position).getEmail(), listaTiendas.get(position).getWeb(), listaTiendas.get(position).getStoreImage(), listaTiendas.get(position).getTags()));
                    }
                }
            });

            if (position % 2 == 0) {
                convertView.setBackgroundColor(mContext.getResources().getColor(R.color.recyclerOpcionesFragment));
            } else {
                convertView.setBackgroundColor(mContext.getResources().getColor(R.color.fondoRecycler));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return listaTiendasSugerencia.size();

    }

    @Nullable
    @Override
    public Stores getItem(int position) {
        return listaTiendasSugerencia.get(position);
    }


    @Override
    public long getItemId(int position) {
        return Long.parseLong(listaTiendasSugerencia.get(position).getStoreNumber());
    }


    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    listaTiendas.clear();
                    for (Stores tienda : RGlobal.tiendas) {
                        if (StringUtils.stripAccents(tienda.getName()).startsWith(constraint.toString().toLowerCase())) {
                            listaTiendas.add(tienda);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = listaTiendas;
                    filterResults.count = listaTiendas.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((Stores) resultValue).getName();
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listaTiendasSugerencia.clear();
                if (results != null && results.count > 0) {
                    // avoids unchecked cast warning when using mDepartments.addAll((ArrayList<Department>) results.values);
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof Stores) {
                            listaTiendasSugerencia.add((Stores) object);
                        }
                    }
                } else if (constraint == null) {
                    // no filter, add entire original list back in


                    listaTiendasSugerencia.addAll(RGlobal.tiendas);
                }
                notifyDataSetChanged();
            }
        };
    }


}
