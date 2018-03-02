package com.app.hance.winjer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;

public class lab extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        TextView text= findViewById(R.id.about);
        text.setText(splash.info.getLab_info());

    }

    public void bookservice(View v)
    {
        Intent i = new Intent(this,location_schedule.class);
        i.putExtra("type","lab");
        startActivity(i);
    }
}
