package com.app.hance.winjer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;

public class electrical extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrical);

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        TextView text=(TextView)findViewById(R.id.about);
        text.setText(splash.info.getElectrical_info());

    }
}
