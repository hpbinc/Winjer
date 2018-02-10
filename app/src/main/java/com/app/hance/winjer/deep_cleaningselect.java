package com.app.hance.winjer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import me.anwarshahriar.calligrapher.Calligrapher;
import worker8.com.github.radiogroupplus.RadioGroupPlus;

public class deep_cleaningselect extends AppCompatActivity implements View.OnClickListener {

    String url="http://tnpmace.com/testingAPI/Booking/listDeepCleaningServices";

    RequestQueue requestQueue;
    cleaningdata apartcleaningdata,homecleaningdata;

    RadioGroupPlus mRadioGroupPlus;

    private int[] btn_id = {R.id.date1, R.id.date2};

    Button date[];

    private Button btn_unfocus;

    private void setFocus(Button btn_unfocus, Button btn_focus){

        btn_unfocus.setBackground(getDrawable(R.drawable.buttonstyle2));
        btn_focus.setTextColor(Color.BLACK);
        btn_focus.setBackground(getDrawable(R.drawable.button_transparent));
        this.btn_unfocus = btn_focus;
    }

    @Override
    public void onClick(View v) {
        //setForcus(btn_unfocus, (Button) findViewById(v.getId()));
        //Or use switch
        switch (v.getId()){
            case R.id.date1 :
                setFocus(btn_unfocus, date[0]);
                break;

            case R.id.date2 :
                setFocus(btn_unfocus, date[1]);
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_cleaningselect);

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);


        date = new Button[2];
        for(int i = 0; i < date.length; i++){
            date[i] = findViewById(btn_id[i]);
            //date[i].setBackgroundColor(Color.rgb(207, 207, 207));
            date[i].setOnClickListener(this);
        }


        btn_unfocus = date[0];

        setFocus(btn_unfocus,date[0]);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mRadioGroupPlus = findViewById(R.id.radio_group_plus);
        mRadioGroupPlus.setOnCheckedChangeListener(new RadioGroupPlus.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroupPlus radioGroupPlus, @IdRes int i) {
                Log.i("RadioGroupPlus", "onCheckedChanged:");
                // Add your logic here
            }
        });

      //get prices from server

             apartcleaningdata=new cleaningdata();
                    apartcleaningdata.setHome_type("apartment");

             homecleaningdata=new cleaningdata();
                    homecleaningdata.setHome_type("home");

        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{



                            Log.e("Response", response.toString());

                            JSONObject jsonObject_apart = response.getJSONObject("appartments");
                            JSONObject jsonObject_home = response.getJSONObject("home");

                            JSONArray apart=jsonObject_apart.getJSONArray("bhk");

                            for(int i=0; i < apart.length(); i++) {

                                JSONObject jsonObject = apart.getJSONObject(i);

                                String bhk = jsonObject.getString("item");
                                float amount=Float.valueOf(jsonObject.getString("amount"));
                                float discount=Float.valueOf(jsonObject.getString("discount"));

                                if (bhk.equals("1bhk"))
                                    apartcleaningdata.setbhk(1,amount,discount);
                                else if(bhk.equals("2bhk"))
                                    apartcleaningdata.setbhk(2,amount,discount);
                                else if(bhk.equals("3bhk"))
                                    apartcleaningdata.setbhk(3,amount,discount);
                                else if(bhk.equals("4bhk"))
                                    apartcleaningdata.setbhk(4,amount,discount);
                                else if(bhk.equals("5bhk"))
                                    apartcleaningdata.setbhk(5,amount,discount);

                                Log.e("\n\n\nResponse", jsonObject.toString());



                            }

                            JSONArray home=jsonObject_home.getJSONArray("bhk");

                            for(int i=0; i < home.length(); i++) {

                                JSONObject jsonObject = home.getJSONObject(i);

                                String bhk = jsonObject.getString("item");
                                Log.e("Hashim",bhk);
                                float amount=Float.valueOf(jsonObject.getString("amount"));
                                float discount=Float.valueOf(jsonObject.getString("discount"));

                                if (bhk.equals("1bhk"))
                                    homecleaningdata.setbhk(1,amount,discount);
                                else if(bhk.equals("2bhk"))
                                    homecleaningdata.setbhk(2,amount,discount);
                                else if(bhk.equals("3bhk"))
                                    homecleaningdata.setbhk(3,amount,discount);
                                else if(bhk.equals("4bhk"))
                                    homecleaningdata.setbhk(4,amount,discount);
                                else if(bhk.equals("5bhk"))
                                    homecleaningdata.setbhk(5,amount,discount);

                                Log.e("\n\n\nResponse", jsonObject.toString());

                            }

                         //   home=jsonObject_home.getJSONArray("others");
                         //   apart=jsonObject_apart.getJSONArray("others");

                        //    homecleaningdata.setothers(Float.parseFloat(home.getString(0)),Float.parseFloat(home.getString(1)),Float.parseFloat(home.getString(2)),Float.parseFloat(home.getString(3)));
                        //    apartcleaningdata.setothers(Float.parseFloat(apart.getString(0)),Float.parseFloat(apart.getString(1)),Float.parseFloat(apart.getString(2)),Float.parseFloat(apart.getString(3)));



                        for(int i=1;i<5;i++)
                        {

                            Log.e("home value"," "+homecleaningdata.getpricebhk(i) +" "+homecleaningdata.getdiscountbhk(i));
                            Log.e("apart value"," "+apartcleaningdata.getpricebhk(i)+"  "+homecleaningdata.getdiscountbhk(i));



                        }




                        }catch(JSONException e)
                        {e.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");

                    }
                }
        );
        requestQueue.add(jor);




    }

    public void onOrderClicked(View view) {
        if (R.id.rb_1bhk == mRadioGroupPlus.getCheckedRadioButtonId()) {
            Toast.makeText(this, ""+homecleaningdata.getpricebhk(1), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(deep_cleaningselect.this,deep_cleaning.class);
            startActivity(i);
        } else if (R.id.rb_2bhk == mRadioGroupPlus.getCheckedRadioButtonId()) {
            Toast.makeText(this, ""+homecleaningdata.getpricebhk(2), Toast.LENGTH_SHORT).show();
        } else if (R.id.rb_3bhk == mRadioGroupPlus.getCheckedRadioButtonId()) {
            Toast.makeText(this, ""+homecleaningdata.getpricebhk(3), Toast.LENGTH_SHORT).show();
        } else if (R.id.rb_4bhk == mRadioGroupPlus.getCheckedRadioButtonId()) {
            //Toast.makeText(basic_cleaning_old.this, "Espresso", Toast.LENGTH_SHORT).show();
        }  else {
            //Toast.makeText(basic_cleaning_old.this, "No Drinks :(", Toast.LENGTH_SHORT).show();
        }
    }
}