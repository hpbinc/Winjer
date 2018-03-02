package com.app.hance.winjer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.shadowfax.proswipebutton.ProSwipeButton;
import me.anwarshahriar.calligrapher.Calligrapher;

public class place_order extends AppCompatActivity {

    String url="http://tnpmace.com/testingAPI/Booking/bookService";
    RequestQueue requestQueue;

    String hometype,land,gpsx,gpsy,dateday,time,house,locality,pin,mobile,type,comments;
    String bedroom,kitchen,hallno,balconyno,bathroomno,amount;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        Intent intent = getIntent();

        type=intent.getStringExtra("type");
        house=intent.getStringExtra("address");
        mobile=intent.getStringExtra("mobile");
        dateday=intent.getStringExtra("date");
        time=intent.getStringExtra("time");
        land=intent.getStringExtra("landmark");
        gpsx=intent.getStringExtra("GPS-X");
        gpsy=intent.getStringExtra("GPS-Y");
        comments=intent.getStringExtra("comments");

        if(type.equals("basic cleaning") || type.equals("deep cleaning"))
        {
            bedroom= intent.getStringExtra("bedroom");
            bathroomno= intent.getStringExtra("bathroom");
            kitchen= intent.getStringExtra("kitchen");
            hallno= intent.getStringExtra("hallno");
            balconyno= intent.getStringExtra("balconyno");
            hometype=intent.getStringExtra("hometype");
            amount= intent.getStringExtra("amount");
        }



        //bundle = getIntent().getExtras();
        //type = bundle.getString("type");
        //house = bundle.getString("address");
        //mobile = bundle.getString("mobile");
        //dateday = bundle.getString("date");
        //time = bundle.getString("time");
        //land = bundle.getString("landmark");
        //gpsx = bundle.getString("GPS-X");
        //gpsy = bundle.getString("GPS-Y");
        //comments = bundle.getString("comments");


        //if(type.equals("basic cleaning")) {
            //bedroom = bundle.getInt("bedroom");
            //bathroomno = bundle.getInt("bathroom");
            //kitchen = bundle.getInt("kitchen");
            //hallno = bundle.getInt("hallno");
            //balconyno = bundle.getInt("balconyno");
            //hometype = bundle.getString("hometype");
            //amount = bundle.getFloat("amount");
        //}

        //Toast.makeText(this, ""+type, Toast.LENGTH_SHORT).show();

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);


        /*
        final ProSwipeButton proSwipeBtn = findViewById(R.id.confirm);
        proSwipeBtn.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                // user has swiped the btn. Perform your async operation now
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // task success! show TICK icon in ProSwipeButton
                        proSwipeBtn.showResultIcon(true); // false if task failed
                        Intent i = new Intent(place_order.this,realfirst.class);
                        startActivity(i);
                    }
                }, 2000);
            }
        });
     */

    }


    public void confirm(View v)
    {

        TextView h = findViewById(R.id.house);
        h.setText(house);
        TextView l = findViewById(R.id.locality);
        l.setText(locality);
        TextView p = findViewById(R.id.pin);
        p.setText(pin);
        TextView m = findViewById(R.id.mobile);
        m.setText(mobile);
        TextView i = findViewById(R.id.item);
        i.setText(type);
        TextView t = findViewById(R.id.type);
        t.setText(type);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", type);
            jsonObject.put("date", "02-03-2018");
            jsonObject.put("time", "morning");
            jsonObject.put("address", house);
            jsonObject.put("landmark", land);
            jsonObject.put("gpsx", gpsx);
            jsonObject.put("gpsy", gpsy);
            jsonObject.put("mobileNo", mobile);
            jsonObject.put("comments", comments);
            //jsonObject.put("auth-key", "123456789");
            jsonObject.put("paymentStatus", "COD");

            if(type.equals("basic cleaning") || type.equals("deep cleaning")) {
                jsonObject.put("homeType", hometype);
                jsonObject.put("bedroom", bedroom);
                jsonObject.put("balcony", balconyno);
                jsonObject.put("kitchen", kitchen);
                jsonObject.put("hall", hallno);
                jsonObject.put("bathroom",bathroomno);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Response", response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");
                        Log.e("error", String.valueOf(error));

                    }
                }
        );
        requestQueue.add(jor);

    }
}
