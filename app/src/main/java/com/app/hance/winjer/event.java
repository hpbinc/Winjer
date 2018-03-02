package com.app.hance.winjer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.anwarshahriar.calligrapher.Calligrapher;

public class event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);
    }

    public void bookservice(View v)
    {
        Intent i = new Intent(this,location_schedule.class);
        i.putExtra("type","eventmanagement");
        startActivity(i);
    }
}
