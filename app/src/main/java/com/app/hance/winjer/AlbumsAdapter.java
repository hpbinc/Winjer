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

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {


    public int no;

    private Context mContext;
    private List<Album> albumList;
    private String[] titles = {"BASIC CLEANING","DEEP CLEANING","PARTY CLEANING","WASHING AND IRONING","DRY CLEANING",
            "AC SERVICE","ELECTRICAL","PLUMBING","MOBILE REPAIR","TV REPAIR","PHOTOGRAPHY","EVENT MANAGEMENT",
            "TAILORING","CAKE","PAINTING","LAB","ROOF WORK"};

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    Log.e("hance",":::" + titles[position]);

                    Log.e("xml",""+no+"  "+R.layout.album_card);

                    if(position==0 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), basic_cleaning_old.class);
                        v.getContext().startActivity(intent);
                    }else if(position==1 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), deep_cleaningselect.class);
                        v.getContext().startActivity(intent);
                    }

                    else if(position==2 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), afterparty.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==3 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), washing_ironing.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==4 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), drycleaning.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==5 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), acrepair.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==6 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), electrical.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==7 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), plumbing.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==8 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), mobile.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==9 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), tvrepair.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==10 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), photography.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==11 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), event.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==12 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), tailoring.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==13 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), cake.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==14 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), painting.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==15 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), lab.class);
                        v.getContext().startActivity(intent);
                    }
                    else if(position==16 && no == R.layout.album_card)
                    {
                        Intent intent = new Intent (v.getContext(), roofwork.class);
                        v.getContext().startActivity(intent);
                    }

                    if(position==0 && no == R.layout.single_card2)
                    {
                        Intent intent = new Intent (v.getContext(), basic_cleaning_old.class);
                        v.getContext().startActivity(intent);
                    }else if(position==1 && no == R.layout.single_card2)
                    {
                        Intent intent = new Intent (v.getContext(), deep_cleaningselect.class);
                        v.getContext().startActivity(intent);
                    }

                    else if(position==2 && no == R.layout.single_card2)
                    {
                        Intent intent = new Intent (v.getContext(), sofa.class);
                        v.getContext().startActivity(intent);
                    }

                    else if(position==3 && no == R.layout.single_card2)
                    {
                        Intent intent = new Intent (v.getContext(), carpet.class);
                        v.getContext().startActivity(intent);
                    }

                    else if(position==4 && no == R.layout.single_card2)
                    {
                        Intent intent = new Intent (v.getContext(), commercial.class);
                        v.getContext().startActivity(intent);
                    }

                    else if(position==5 && no == R.layout.single_card2)
                    {
                        Intent intent = new Intent (v.getContext(), housekeeping.class);
                        v.getContext().startActivity(intent);
                    }
                   /* else
                    {
                        Snackbar.make(v, "Click detected on item " + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        Intent intent = new Intent(v.getContext(), carpet.class);
                        v.getContext().startActivity(intent);
                    }*/
                   /* Intent intent = new Intent (v.getContext(), basic_cleaning_old.class);
                    intent.putExtra("titles", titles[position]);
                    v.getContext().startActivity(intent);*/

                }
            });
        }
    }


    public AlbumsAdapter(Context mContext, List<Album> albumList , int no) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.no = no;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(no, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }
}