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
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by HANCE on 02/01/2018.
 */

public class cakeadapter extends RecyclerView.Adapter<cakeadapter.MyViewHolder> {

    private Context mContext;
    private List<cakedata> albumList;


    public cakeadapter(Context mContext, List<cakedata> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cake, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
       // Album album = albumList.get(position);
         holder.title.setText(albumList.get(position).getImageTitle());
         holder.price.setText(""+albumList.get(position).getprice());
         holder.discount.setText("Market price ₹"+albumList.get(position).getDiscount());
        Log.e("HHAIM",""+albumList.get(position).getImageUrl());
        // loading album cover using Glide library
        Glide.with(mContext).load(albumList.get(position).getImageUrl())
                .placeholder(R.drawable.cake)
                .error(R.drawable.cake)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        // log exception
                        Log.e("TAG", "Error loading image", e);
                        return false; // important to return false so the error placeholder can be placed
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.thumbnail);



    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count,price,discount;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.cakename);
            price = view.findViewById(R.id.price);
            discount = view.findViewById(R.id.oldprice);
            thumbnail = view.findViewById(R.id.image);

    /*    Typeface zeronero= Typeface.createFromAsset(view.getContext().getAssets(),"fonts/zeronero.ttf");
            Typeface anson=Typeface.createFromAsset(view.getContext().getAssets(),"fonts/Anson-Regular.otf");
            Typeface gillsans=Typeface.createFromAsset(view.getContext().getAssets(),"fonts/gillsans.ttf");
            Typeface vampire=Typeface.createFromAsset(view.getContext().getAssets(),"fonts/vampire.ttf");

            title.setTypeface(anson);
*/


            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    Log.e("hance",":::" + position);
                    Snackbar.make(v, "Click detected on item " + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    Intent intent = new Intent (v.getContext(), cake.class);
                    v.getContext().startActivity(intent);
                   /* Intent intent = new Intent (v.getContext(), house_cleaning.class);
                    intent.putExtra("titles", titles[position]);
                    v.getContext().startActivity(intent);*/

                }
            });
        }
    }
}