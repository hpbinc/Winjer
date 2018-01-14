package com.app.hance.winjer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import worker8.com.github.radiogroupplus.RadioGroupPlus;

public class house_cleaning extends AppCompatActivity {
    RadioGroupPlus mRadioGroupPlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_cleaning);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
            Toast.makeText(house_cleaning.this, "Latte", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(house_cleaning.this,secondpage.class);
            startActivity(i);
        } else if (R.id.rb_mocha == mRadioGroupPlus.getCheckedRadioButtonId()) {
            Toast.makeText(house_cleaning.this, "Mocha", Toast.LENGTH_SHORT).show();
        } else if (R.id.rb_americano == mRadioGroupPlus.getCheckedRadioButtonId()) {
            Toast.makeText(house_cleaning.this, "Americano", Toast.LENGTH_SHORT).show();
        } else if (R.id.rb_espresso == mRadioGroupPlus.getCheckedRadioButtonId()) {
            Toast.makeText(house_cleaning.this, "Espresso", Toast.LENGTH_SHORT).show();
        } else if (R.id.rb_orange == mRadioGroupPlus.getCheckedRadioButtonId()) {
            Toast.makeText(house_cleaning.this, "Orange", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(house_cleaning.this, "No Drinks :(", Toast.LENGTH_SHORT).show();
        }
    }
}