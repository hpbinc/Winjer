package com.winjer.app;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import static android.R.attr.id;

public class largeimage extends AppCompatActivity {
    SubsamplingScaleImageView view;

    public void close(View view)
    {
        finish();
        overridePendingTransition(0,R.anim.zoom_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_largeimage);

        Bundle bundle = getIntent().getExtras();
        String url  = bundle.getString("url");

        view = findViewById(R.id.imageView);

      //imageView.setImage(ImageSource.uri("https://scontent.fbom4-2.fna.fbcdn.net/v/t1.0-9/29340224_2079308052352948_6394206070285271040_n.jpg?oh=54d46fea81f0342705073d1f127d422f&oe=5B2FFC15"));

        Glide.with(view.getContext()).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {


            @Override public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) { view.setImage(ImageSource.bitmap(resource)); } });
    }

    @Override
    public void onBackPressed() {
     //   Log.d("Winjer", "onBackPressed Called");
        finish();
        overridePendingTransition(0,R.anim.zoom_out);
    }
}
