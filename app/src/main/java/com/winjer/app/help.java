package com.winjer.app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;

public class help extends AppCompatActivity {

    String number = "8156888880";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        TextView textView = findViewById(R.id.number);
        textView.setText(""+number);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);
    }

    public void call(View v){

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));

        startActivity(intent);

    }
}
