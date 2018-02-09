package com.app.hance.winjer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import in.shadowfax.proswipebutton.ProSwipeButton;
import me.anwarshahriar.calligrapher.Calligrapher;

public class place_order extends AppCompatActivity {


    public static final String MY_PREFS_NAME = "hpbPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);


        final ProSwipeButton proSwipeBtn = findViewById(R.id.confirm);
        proSwipeBtn.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                // user has swiped the btn. Perform your async operation now
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // task success! show TICK icon in ProSwipeButton
                        proSwipeBtn.showResultIcon(true); // false if task failed
                        Intent i = new Intent(place_order.this,basic_cleaning_old.class);
                        startActivity(i);
                    }
                }, 2000);
            }
        });


        TextView house = findViewById(R.id.house);
        TextView locality = findViewById(R.id.locality);
        TextView pin = findViewById(R.id.pin);
        TextView mobile = findViewById(R.id.mobile);
        TextView item = findViewById(R.id.item);
        TextView type = findViewById(R.id.type);


    }
}
