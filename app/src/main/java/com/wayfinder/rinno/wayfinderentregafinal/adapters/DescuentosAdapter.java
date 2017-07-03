package com.wayfinder.rinno.wayfinderentregafinal.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wayfinder.rinno.wayfinderentregafinal.R;
import com.wayfinder.rinno.wayfinderentregafinal.model.Descuentos;
import com.wayfinder.rinno.wayfinderentregafinal.model.MessageNew;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by tecnova on 11-04-2017.
 */

public class DescuentosAdapter extends RecyclerView.Adapter<DescuentosAdapter.ViewHolder> {

    private List<Descuentos> itemList;
    private Context context;

    public DescuentosAdapter(Context context, List<Descuentos> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_fragment_descuentos, null);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //holder.imgDescuentos.setImageResource(R.drawable.descuentopopup);
       holder.imgDescuentos.setImageURI(itemList.get(position).getImgURLWayfinder());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageNew(itemList.get(position).getImgURLWayfinder(),7));
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView imgDescuentos;

        public ViewHolder(View itemView) {
            super(itemView);
            imgDescuentos = (SimpleDraweeView) itemView.findViewById(R.id.imgDescuentos);
        }

        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}

