package com.app.hance.winjer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;

public class sofa extends AppCompatActivity implements View.OnClickListener {

    Button seat1,seat2;
    TextView seats,price;
    int seatno=3;
    float cost=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofa);

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        seats = findViewById(R.id.seats);
        price = findViewById(R.id.price);

        seat1= findViewById(R.id.seat1);
        seat2= findViewById(R.id.seat2);

        seat1.setOnClickListener(this);
        seat2.setOnClickListener(this);

        seats.setText("Seats   : "+""+seatno);
        price.setText("₹ "+seatno*cost);
    }

    public void onOrderClicked(View v)
    {
        Intent i = new Intent(this,location_schedule.class);
        i.putExtra("type","afterparty");
        i.putExtra("seatno",seatno);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.seat1 :

                if(seatno > 3)
                    seatno--;
                seats.setText("Seats   : "+""+seatno);
                price.setText("₹ "+seatno*cost);
                break;

            case R.id.seat2 :

                seatno++;
                seats.setText("Seats   : "+""+seatno);
                price.setText("₹ "+seatno*cost);
                break;

        }
    }
}
