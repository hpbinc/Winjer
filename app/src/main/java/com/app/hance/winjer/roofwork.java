package com.app.hance.winjer;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jaredrummler.materialspinner.MaterialSpinner;

import me.anwarshahriar.calligrapher.Calligrapher;

public class roofwork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roofwork);

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        MaterialSpinner spinner = findViewById(R.id.spinner);
        spinner.setItems("500 sq.ft", "1000 sq.ft","1500 sq.ft","2000 sq.ft", "2500 sq.ft", "3000 sq.ft","3500 sq.ft","4000 sq.ft","4500 sq.ft");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void bookservice(View v)
    {
        Intent i = new Intent(this,location_schedule.class);
        startActivity(i);
    }
}
