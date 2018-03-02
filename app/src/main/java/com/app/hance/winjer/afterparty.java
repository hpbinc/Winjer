package com.app.hance.winjer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import me.anwarshahriar.calligrapher.Calligrapher;

public class afterparty extends AppCompatActivity {

    TextView price,oldprice;
    String area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterparty);

        price = findViewById(R.id.price);
        oldprice = findViewById(R.id.oldprice);

        price.setText("₹ 2000");
        oldprice.setText("( ₹2000 50% Discount )");

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        area = "500 - 999 sq.ft";
        MaterialSpinner spinner = findViewById(R.id.spinner);
        spinner.setItems("500 - 999 sq.ft", "1000 - 1499 sq.ft","1500 - 1999 sq.ft","2000 - 2499 sq.ft", "2500 - 2999 sq.ft", "3000 - 3499 sq.ft","3500 -3999 sq.ft","4000 -4499 sq.ft","4500 - 4999 sq.ft");
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
        i.putExtra("type","afterparty");
        i.putExtra("area",area);
        startActivity(i);
    }
}
