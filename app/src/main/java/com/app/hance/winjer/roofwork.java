package com.app.hance.winjer;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;


import me.anwarshahriar.calligrapher.Calligrapher;

public class roofwork extends AppCompatActivity {

    TextView price,oldprice;
    String area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roofwork);

        price = findViewById(R.id.price);
        oldprice = findViewById(R.id.oldprice);

        price.setText("₹ 2000");
        oldprice.setText("( ₹2000 50% Discount )");

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);


        MaterialSpinner spinner = findViewById(R.id.spinner);
        spinner.setItems("500 sq.ft", "1000 sq.ft","1500 sq.ft","2000 sq.ft", "2500 sq.ft", "3000 sq.ft","3500 sq.ft","4000 sq.ft","4500 sq.ft");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                area = item;
            }
        });
    }

    public void bookservice(View v)
    {
        Intent i = new Intent(this,location_schedule.class);
        i.putExtra("type","roofwork");
        i.putExtra("area",area);
        startActivity(i);
    }
}
