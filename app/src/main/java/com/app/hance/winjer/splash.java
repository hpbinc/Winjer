package com.app.hance.winjer;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.anwarshahriar.calligrapher.Calligrapher;

public class splash extends AppCompatActivity implements Animation.AnimationListener {

    private ImageView mFbLogoImageView;
    private TextView textView,mimos;
    private Button mNewAccountButton;
    private ImageView mFbLogoStaticImageView;

    private Boolean ANIMATION_ENDED = false;
    private Boolean START_ANIMATION = true;
    private Boolean response_recieved=false;

    public static data info;
    String url="http://tnpmace.com/testingAPI/Booking/listDescriptionOnly";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Recieve service infos..
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{

                            info=new data();


                            Log.e("Response", response.toString());

                            JSONArray jarray = response.getJSONArray("services");


                            for(int i=0; i < jarray.length(); i++) {

                                JSONObject jsonObject = jarray.getJSONObject(i);

                                String type = jsonObject.getString("type");

                                if(type.equals("plumbing"))
                                {

                                    info.setPlumbing_info(""+jsonObject.get("type_description"));

                                    Log.e("\n\n\nResponse", ""+jsonObject.get("type_description"));

                                }
                                if(type.equals("TV"))
                                {

                                    info.setTvrepair_info(""+jsonObject.get("type_description"));


                                    Log.e("\n\n\nResponse", ""+jsonObject.get("type_description"));

                                }
                                if(type.equals("electrical"))
                                {
                                    info.setElectrical_info(""+jsonObject.get("type_description"));


                                    Log.e("\n\n\nResponse", ""+jsonObject.get("type_description"));

                                }
                                if(type.equals("lab"))
                                {

                                    info.setLab(""+jsonObject.get("type_description"));


                                    Log.e("\n\n\nResponse", ""+jsonObject.get("type_description"));

                                }
                                if(type.equals("ac"))
                                {

                                    info.setAcinfo(""+jsonObject.get("type_description"));


                                    Log.e("\n\n\nResponse", ""+jsonObject.get("type_description"));

                                }
                                if(type.equals("drycleaning"))
                                {

                                    info.setDrycleaning_info(""+jsonObject.get("type_description"));


                                    Log.e("\n\n\nResponse", ""+jsonObject.get("type_description"));

                                }
                                if(type.equals("painting"))
                                {

                                    info.setPainting_info(""+jsonObject.get("type_description"));


                                    Log.e("\n\n\nResponse", ""+jsonObject.get("type_description"));

                                }
                                if(type.equals("mobile_repair"))
                                {

                                    info.setMobilerepair_info(""+jsonObject.get("type_description"));


                                    Log.e("\n\n\nResponse", ""+jsonObject.get("type_description"));

                                }


                            }

                            if( ANIMATION_ENDED)
                            {

                                Intent intent = new Intent(getApplicationContext(),realfirst.class);
                                startActivity(intent);
                                finish();

                            }
                            else
                            {

                                response_recieved=true;


                            }


                        }catch(JSONException e)
                        {   e.printStackTrace();
                            Toast.makeText(splash.this, " Network Error Occured ", Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");

                    }
                }
        );
        requestQueue.add(jor);



        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        if(savedInstanceState != null) {
            START_ANIMATION = savedInstanceState.getBoolean("START_ANIMATION");
        }

        mFbLogoImageView = findViewById(R.id.fbLogoImageView);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)

            textView = findViewById(R.id.textView);
            mimos= findViewById(R.id.mimos);

        //mNewAccountButton = (Button) findViewById(R.id.newAccountButton);
        mFbLogoStaticImageView = findViewById(R.id.fbLogoStaticImageView);

        if(START_ANIMATION) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                textView.setVisibility(View.GONE);
                mimos.setVisibility(View.GONE);
            //mNewAccountButton.setVisibility(View.GONE);
            mFbLogoStaticImageView.setVisibility(View.GONE);

            Animation moveFBLogoAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
            moveFBLogoAnimation.setFillAfter(true);
            moveFBLogoAnimation.setAnimationListener(this);
            mFbLogoImageView.startAnimation(moveFBLogoAnimation);
        }
        else {
            mFbLogoImageView.setVisibility(View.GONE);
        }

        final View activityRootView = findViewById(R.id.mainConstraintLayout);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(ANIMATION_ENDED || !START_ANIMATION) {
                    int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                    if (heightDiff > dpToPx(splash.this, 200)) {
                        //Soft keyboard is shown
                        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)

                            textView.setVisibility(View.GONE);
                            mimos.setVisibility(View.GONE);
                    } else {
                        //Soft keyboard is hidden
                        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                            textView.setVisibility(View.VISIBLE);
                            mimos.setVisibility(View.VISIBLE);
                    }
                }
            }
        });




        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {

                    sleep(2000);
                    if (response_recieved) {
                        Intent intent = new Intent(getApplicationContext(), realfirst.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        myThread.start();


    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mFbLogoStaticImageView.setVisibility(View.VISIBLE);
        mFbLogoImageView.clearAnimation();
        mFbLogoImageView.setVisibility(View.GONE);

        textView.setAlpha(0f);
        textView.setVisibility(View.VISIBLE);
        mimos.setAlpha(0f);
        mimos.setVisibility(View.VISIBLE);

        //  mNewAccountButton.setAlpha(0f);
        //  mNewAccountButton.setVisibility(View.VISIBLE);

        int mediumAnimationTime = getResources().getInteger(android.R.integer.config_mediumAnimTime);


        textView.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

        mimos.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);

      /*  mNewAccountButton.animate()
                .alpha(1f)
                .setDuration(mediumAnimationTime)
                .setListener(null);*/

        ANIMATION_ENDED = true;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("START_ANIMATION", false);
    }

    public static class Utility {
        public static int calculateNoOfColumns(Context context) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (dpWidth / 100);
            return noOfColumns;
        }
    }


}


