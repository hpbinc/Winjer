package com.app.hance.winjer;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by HANCE on 02/01/2018.
 */

public class washing_ironing_adapter extends RecyclerView.Adapter<washing_ironing_adapter.MyViewHolder> {

    private Context mContext;

    private List<washing_ironing_data> mydata;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text, price,count;


        public MyViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.text);
            count = view.findViewById(R.id.count);
            price = view.findViewById(R.id.price);

            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();


                }
            });
        }
    }


    public washing_ironing_adapter(Context mContext, List<washing_ironing_data> mydata) {
        this.mContext = mContext;
        this.mydata = mydata;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlecarddryclean, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //Album album = albumList.get(position);
        holder.text.setText(mydata.get(position).name);
        holder.price.setText("₹ "+mydata.get(position).price);
        holder.count.setText(""+mydata.get(position).count);
       Log.e("hashim","view Added");
        // loading album cover using Glide library
       // Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

    }


    @Override
    public int getItemCount() {
        return mydata.size();
    }
}