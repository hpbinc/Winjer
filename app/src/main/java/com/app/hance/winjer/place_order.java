package com.app.hance.winjer;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class place_order extends AppCompatActivity {


    public static final String MY_PREFS_NAME = "hpbPrefsFile";



    public void confirm(View v)
    {


        Toast.makeText(this, "Booking success...", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);


        final ProSwipeButton proSwipeBtn = (ProSwipeButton) findViewById(R.id.awesome_btn);
        proSwipeBtn.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                // user has swiped the btn. Perform your async operation now
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // task success! show TICK icon in ProSwipeButton
                        proSwipeBtn.showResultIcon(true); // false if task failed
                    }
                }, 2000);
            }
        });



        String h,l,p,m,it,ty;

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        h = prefs.getString("house","");
        l = prefs.getString("locality","");
        p = prefs.getString("pin","");
        m = prefs.getString("mobile","");
        it = prefs.getString("item","");
        ty = prefs.getString("type","");

        TextView house = (TextView)findViewById(R.id.house);
        TextView locality = (TextView)findViewById(R.id.locality);
        TextView pin = (TextView)findViewById(R.id.pin);
        TextView mobile = (TextView)findViewById(R.id.mobile);
        TextView item = (TextView)findViewById(R.id.item);
        TextView type = (TextView)findViewById(R.id.type);

        house.setText(" "+h);
        locality.setText(" "+l);
        pin.setText(" "+p);
        mobile.setText(" "+m);
        item.setText(" "+it);
        type.setText(" "+ty);


    }
}
