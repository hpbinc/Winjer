package com.winjer.app;

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

public class banAdapter extends RecyclerView.Adapter<banAdapter.MyViewHolder> {

    private Context mContext;
    private List<addData> albumList;


    public banAdapter(Context mContext, List<addData> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlecard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // Album album = albumList.get(position);
      //  Log.e("HHAIM",""+albumList.get(position).getImageUrl());
        // loading album cover using Glide library
        Glide.with(mContext).load(albumList.get(position).getImageUrl())
                .placeholder(R.drawable.checkban)

                .error(R.drawable.checkban)
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
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            thumbnail = view.findViewById(R.id.thumbnail);

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
                    String id = albumList.get(position).getId();
                    String name = albumList.get(position).getImageTitle();
                    String url = albumList.get(position).getImageUrl();
                    Log.e("url",url);

                   // Snackbar.make(v, "Click detected on item " + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
//                    Intent intent = new Intent (v.getContext(), dialogbox.class);
//                    intent.putExtra("id",id);
//                    intent.putExtra("title",name);
//                    intent.putExtra("description",description);
//                    intent.putExtra("url",url);
//                    v.getContext().startActivity(intent);
                   /* Intent intent = new Intent (v.getContext(), house_cleaning.class);
                    intent.putExtra("titles", titles[position]);
                    v.getContext().startActivity(intent);*/

                }
            });
        }
    }
}