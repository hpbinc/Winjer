package com.app.hance.winjer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import me.anwarshahriar.calligrapher.Calligrapher;
import worker8.com.github.radiogroupplus.RadioGroupPlus;

public class basic_cleaning_old extends AppCompatActivity implements View.OnClickListener {

    RadioGroupPlus mRadioGroupPlus;

    private int[] btn_id = {R.id.date1, R.id.date2};

    Button date[];

    private Button btn_unfocus;

    private void setFocus(Button btn_unfocus, Button btn_focus){

        btn_unfocus.setBackground(getDrawable(R.drawable.buttonstyle2));
        btn_focus.setTextColor(Color.BLACK);
        btn_focus.setBackground(getDrawable(R.drawable.button_transparent));
        this.btn_unfocus = btn_focus;
    }

    @Override
    public void onClick(View v) {
        //setForcus(btn_unfocus, (Button) findViewById(v.getId()));
        //Or use switch
        switch (v.getId()){
            case R.id.date1 :
                setFocus(btn_unfocus, date[0]);
                break;

            case R.id.date2 :
                setFocus(btn_unfocus, date[1]);
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_cleaning_old);

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        date = new Button[2];
        for(int i = 0; i < date.length; i++){
            date[i] = findViewById(btn_id[i]);
            //date[i].setBackgroundColor(Color.rgb(207, 207, 207));
            date[i].setOnClickListener(this);
        }


        btn_unfocus = date[0];

        setFocus(btn_unfocus,date[0]);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mRadioGroupPlus = findViewById(R.id.radio_group_plus);
        mRadioGroupPlus.setOnCheckedChangeListener(new RadioGroupPlus.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroupPlus radioGroupPlus, @IdRes int i) {
                Log.i("RadioGroupPlus", "onCheckedChanged:");
                // Add your logic here
            }
        });

    }

    public void onOrderClicked(View view) {
        if (R.id.rb_1bhk == mRadioGroupPlus.getCheckedRadioButtonId()) {
            //Toast.makeText(basic_cleaning_old.this, "Latte", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(basic_cleaning_old.this,basic_cleaning.class);
            startActivity(i);
        } else if (R.id.rb_2bhk == mRadioGroupPlus.getCheckedRadioButtonId()) {
            //Toast.makeText(basic_cleaning_old.this, "Mocha", Toast.LENGTH_SHORT).show();
        } else if (R.id.rb_3bhk == mRadioGroupPlus.getCheckedRadioButtonId()) {
            //Toast.makeText(basic_cleaning_old.this, "Americano", Toast.LENGTH_SHORT).show();
        } else if (R.id.rb_4bhk == mRadioGroupPlus.getCheckedRadioButtonId()) {
            //Toast.makeText(basic_cleaning_old.this, "Espresso", Toast.LENGTH_SHORT).show();
        }  else {
            //Toast.makeText(basic_cleaning_old.this, "No Drinks :(", Toast.LENGTH_SHORT).show();
        }
    }
}