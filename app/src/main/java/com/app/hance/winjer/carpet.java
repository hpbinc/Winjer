package com.app.hance.winjer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;

public class carpet extends AppCompatActivity {

    String area;
    Float cost,rate= Float.valueOf(45);
    TextView about,price;
    EditText sqft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carpet);
        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        about= findViewById(R.id.about);
        price= findViewById(R.id.price);
        sqft= findViewById(R.id.sqft);
        about.setText(splash.info.getCarpet_info());
        price.setText("   ₹45/-");                           //rate
       // price.setText(splash.info.getCarpet_price());

    }

    public void onOrderClicked(View v)
    {
        area = sqft.getText().toString();
        cost = Float.parseFloat(area);
        cost = cost * rate;
        Intent i = new Intent(this,location_schedule.class);
        i.putExtra("type","carpet");
        i.putExtra("sqft",""+sqft);
        i.putExtra("cost",""+cost);
        startActivity(i);
    }
}
