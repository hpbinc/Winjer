package com.app.hance.winjer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import worker8.com.github.radiogroupplus.RadioGroupPlus;

public class house_cleaning extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "hpbPrefsFile";
    RadioGroupPlus mRadioGroupPlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_cleaning);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mRadioGroupPlus = (RadioGroupPlus) findViewById(R.id.radio_group_plus);
        mRadioGroupPlus.setOnCheckedChangeListener(new RadioGroupPlus.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroupPlus radioGroupPlus, @IdRes int i) {
                Log.i("RadioGroupPlus", "onCheckedChanged:");
                // Add your logic here
            }
        });

    }

    public void onOrderClicked(View view) {
        if (R.id.rb_latte == mRadioGroupPlus.getCheckedRadioButtonId()) {

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("type","Basic cleaning");
            editor.putString("cost","₹2500");
            editor.apply();
            //Toast.makeText(house_cleaning.this, "Latte", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(house_cleaning.this,location_schedule.class);
            startActivity(i);
        } else if (R.id.rb_mocha == mRadioGroupPlus.getCheckedRadioButtonId()) {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("type","1 BHK House");
            editor.putString("cost","₹3000");
            editor.apply();
            //Toast.makeText(house_cleaning.this, "Mocha", Toast.LENGTH_SHORT).show();
        } else if (R.id.rb_americano == mRadioGroupPlus.getCheckedRadioButtonId()) {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("type","2 BHK House");
            editor.putString("cost","₹3500");
            editor.apply();
            //Toast.makeText(house_cleaning.this, "Americano", Toast.LENGTH_SHORT).show();
        } else if (R.id.rb_espresso == mRadioGroupPlus.getCheckedRadioButtonId()) {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("type","3 BHK House");
            editor.putString("cost","₹4500");
            editor.apply();
            //Toast.makeText(house_cleaning.this, "Espresso", Toast.LENGTH_SHORT).show();
        } else if (R.id.rb_orange == mRadioGroupPlus.getCheckedRadioButtonId()) {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("type","4 BHK House");
            editor.putString("cost","₹6000");
            editor.apply();
            //Toast.makeText(house_cleaning.this, "Orange", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(house_cleaning.this, "No Drinks :(", Toast.LENGTH_SHORT).show();
        }
    }
}