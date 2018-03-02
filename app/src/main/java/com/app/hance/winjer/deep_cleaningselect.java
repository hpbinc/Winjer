package com.app.hance.winjer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
import worker8.com.github.radiogroupplus.RadioGroupPlus;

public class deep_cleaningselect extends AppCompatActivity implements View.OnClickListener {


    RadioGroupPlus mRadioGroupPlus;
    String hometype="home";
    cleaningdata apartcleaningdata,homecleaningdata;
    Button date[];
    private int[] btn_id = {R.id.date1, R.id.date2};
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
                hometype="home";
                break;

            case R.id.date2 :
                setFocus(btn_unfocus, date[1]);
                hometype="appartments";
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_cleaningselect);

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
            Intent i = new Intent(deep_cleaningselect.this,deep_cleaning.class);
            i.putExtra("bhk","1");
            i.putExtra("hometype",hometype);
            startActivity(i);
        } else if (R.id.rb_2bhk == mRadioGroupPlus.getCheckedRadioButtonId()) {
            Intent i = new Intent(deep_cleaningselect.this,deep_cleaning.class);
            i.putExtra("bhk","2");
            i.putExtra("hometype",hometype);
            startActivity(i);
        } else if (R.id.rb_3bhk == mRadioGroupPlus.getCheckedRadioButtonId()) {
            Intent i = new Intent(deep_cleaningselect.this,deep_cleaning.class);
            i.putExtra("bhk","3");
            i.putExtra("hometype",hometype);
            startActivity(i);
        } else if (R.id.rb_4bhk == mRadioGroupPlus.getCheckedRadioButtonId()) {
            Intent i = new Intent(deep_cleaningselect.this,deep_cleaning.class);
            i.putExtra("bhk","4");
            i.putExtra("hometype",hometype);
            startActivity(i);
        }  else {
            Intent i = new Intent(deep_cleaningselect.this,deep_cleaning.class);
            i.putExtra("bhk","5");
            i.putExtra("hometype",hometype);
            startActivity(i);
        }
    }
}
