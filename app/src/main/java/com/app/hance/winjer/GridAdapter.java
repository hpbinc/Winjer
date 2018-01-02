package com.app.hance.winjer;

/**
 * Created by hance on 02/01/18
 */


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter{


    private int images[];
    private String titles[];

    private Context context;
    private LayoutInflater inflater;

    public GridAdapter(Context context , int images[], String titles[]){


        this.context = context;
        this.images=images;
        this.titles=titles;

    }
    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;
        if(convertView ==null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.single_card,null);
        }

        ImageView imageView = (ImageView)gridView.findViewById(R.id.item_image);

        TextView textView = (TextView)gridView.findViewById(R.id.item_title);

        imageView.setImageResource(images[position]);
        textView.setText(titles[position]);

        return gridView;
    }

}