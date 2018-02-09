package com.app.hance.winjer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;

public class basic_cleaning extends AppCompatActivity implements View.OnClickListener {

    Button halls1,halls2,bathroom1,bathroom2,kitchen1,kitchen2;
    TextView halls,bathrooms,kitchens;
    int hallno=1,bathroomno=1,kitchenno=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_cleaning);

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        halls = (TextView)findViewById(R.id.halls);
        bathrooms = (TextView)findViewById(R.id.bathrooms);
        kitchens = (TextView)findViewById(R.id.kitchens);

        halls1= (Button)findViewById(R.id.halls1);
        halls2= (Button)findViewById(R.id.halls2);

        bathroom1= (Button)findViewById(R.id.bathroom1);
        bathroom2= (Button)findViewById(R.id.bathroom2);

        kitchen1= (Button)findViewById(R.id.kitchen1);
        kitchen2= (Button)findViewById(R.id.kitchen2);

        halls1.setOnClickListener(this);
        halls2.setOnClickListener(this);
        bathroom1.setOnClickListener(this);
        bathroom2.setOnClickListener(this);
        kitchen1.setOnClickListener(this);
        kitchen2.setOnClickListener(this);

        halls.setText("Halls   : "+""+hallno);
        bathrooms.setText("Bathrooms   : "+""+bathroomno);
        kitchens.setText("Kitchens   : "+""+kitchenno);

    }

    public void onOrderClicked(View v)
    {
        Intent i = new Intent(this,location_schedule.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.halls1 :

                if(hallno > 1)
                    hallno--;
                halls.setText("Halls   : "+""+hallno);
                break;

            case R.id.halls2 :

                hallno++;
                halls.setText("Halls   : "+""+hallno);
                break;

            case R.id.bathroom1 :

                if(bathroomno > 1)
                    bathroomno--;
                bathrooms.setText("Bathrooms   : "+""+bathroomno);
                break;

            case R.id.bathroom2 :

                bathroomno++;
                bathrooms.setText("Bathrooms   : "+""+bathroomno);
                break;

            case R.id.kitchen1 :

                if(kitchenno > 1)
                    kitchenno--;
                kitchens.setText("Kitchens   : "+""+kitchenno);
                break;

            case R.id.kitchen2 :

                kitchenno++;
                kitchens.setText("Kitchens   : "+""+kitchenno);
                break;

        }
    }
}
