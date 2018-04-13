package com.winjer.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;

public class tvrepair extends AppCompatActivity {

    ImageView bgdImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvrepair);

        bgdImage = findViewById(R.id.background);

        Glide.with(this).load("http://winjer.in/api/static/images/tvback.jpg")
                .placeholder(R.drawable.loadingstatic)
                .error(R.drawable.loadingstatic)
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
                .into(bgdImage);

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        TextView text= findViewById(R.id.about);
        if(splash.info!=null)
            text.setText(splash.info.getTvrepair_info());
        else
        {
            data.getinfo_fromserver(this,"TV Repair",text);
        }

    }

    public void bookservice(View v)
    {
        Intent i = new Intent(this,location_schedule.class);
        i.putExtra("type","TV Repair");
        startActivity(i);
    }

}
