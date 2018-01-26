package com.app.hance.winjer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by HANCE on 02/01/2018.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    public static final String MY_PREFS_NAME = "hpbPrefsFile";

    public int no;

    private Context mContext;
    private List<Album> albumList;
    private String[] titles = {"BASIC CLEANING","DEEP CLEANING","PARTY CLEANING","WASHING AND IRONING","DRY CLEANING",
            "AC SERVICE","ELECTRICAL","PLUMBING","MOBILE REPAIR","TV REPAIR","PHOTOGRAPHY","EVENT MANAGEMENT",
            "TAILORING","CAKE"};

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

        /*    Typeface zeronero= Typeface.createFromAsset(view.getContext().getAssets(),"fonts/zeronero.ttf");
            Typeface anson=Typeface.createFromAsset(view.getContext().getAssets(),"fonts/Anson-Regular.otf");
            Typeface gillsans=Typeface.createFromAsset(view.getContext().getAssets(),"fonts/gillsans.ttf");
            Typeface vampire=Typeface.createFromAsset(view.getContext().getAssets(),"fonts/vampire.ttf");

            title.setTypeface(anson);
*/
            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    Log.e("hance",":::" + titles[position]);
                    SharedPreferences.Editor editor = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("item", titles[position]);
                    editor.apply();
                    //Snackbar.make(v, "Click detected on item " + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    Intent intent = new Intent (v.getContext(), house_cleaning.class);
                    v.getContext().startActivity(intent);
                   /* Intent intent = new Intent (v.getContext(), house_cleaning.class);
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