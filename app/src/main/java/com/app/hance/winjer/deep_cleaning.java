package com.app.hance.winjer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;

public class deep_cleaning extends AppCompatActivity implements View.OnClickListener {

    String url="http://tnpmace.com/testingAPI/Booking/getCleaning";
    RequestQueue requestQueue;

    Button halls1,halls2,bathroom1,bathroom2,kitchen1,kitchen2,balcony1,balcony2;
    Float bhk_price,hall_price,balcony_price,bathroom_price,kitchen_price,amount;
    TextView halls,bathrooms,kitchens,balconys,amount_text,discount_text,info;
    int hallno=1,bathroomno=1,kitchenno=1,balconyno=1,bhk,discount;
    String hometype;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_cleaning);

        dialog=new ProgressDialog(this);
        dialog.setTitle(" Please wait");
        dialog.show();

        amount_text= findViewById(R.id.price);
        discount_text= findViewById(R.id.oldprice);
        info= findViewById(R.id.about);
        Intent intent = getIntent();

        bhk=Integer.parseInt(intent.getStringExtra("bhk"));
        hometype=intent.getStringExtra("hometype");

        requestQueue = Volley.newRequestQueue(this);

        StringRequest jor = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responsee) {

                        try{

                            Log.e("Response", responsee.toString());
                            JSONObject response=new JSONObject(responsee);

                            bhk_price=Float.parseFloat(response.getString("bedroom"));
                            bathroom_price=Float.parseFloat(response.getString("bathroom"));
                            balcony_price=Float.parseFloat(response.getString("balcony"));
                            hall_price=Float.parseFloat(response.getString("hall"));
                            kitchen_price=Float.parseFloat(response.getString("kitchen"));
                            discount=Integer.parseInt(response.getString("discount"));
                            amount=bhk_price;

                            amount_text.setText("₹ "+amount);
                            discount_text.setText("( Discount "+discount+"% )");
                            //  info.setText(""+response.getString("info"));

                            dialog.dismiss();


                        }catch(Exception e)
                        {e.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error"+error);
                        //stop progress dialog and display error

                    }
                }
        ) {
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("type", "deep");
                params.put("bhk", ""+bhk);
                params.put("homeType",hometype);
                return params;
            }
        };
        requestQueue.add(jor);
        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        halls = findViewById(R.id.halls);
        bathrooms = findViewById(R.id.bathrooms);
        kitchens = findViewById(R.id.kitchens);
        balconys = findViewById(R.id.balconys);

        halls1= findViewById(R.id.halls1);
        halls2= findViewById(R.id.halls2);

        bathroom1= findViewById(R.id.bathroom1);
        bathroom2= findViewById(R.id.bathroom2);

        kitchen1= findViewById(R.id.kitchen1);
        kitchen2= findViewById(R.id.kitchen2);

        balcony1= findViewById(R.id.balcony1);
        balcony2= findViewById(R.id.balcony2);

        halls1.setOnClickListener(this);
        halls2.setOnClickListener(this);
        bathroom1.setOnClickListener(this);
        bathroom2.setOnClickListener(this);
        kitchen1.setOnClickListener(this);
        kitchen2.setOnClickListener(this);
        balcony1.setOnClickListener(this);
        balcony2.setOnClickListener(this);

        halls.setText("Halls   : "+""+hallno);
        bathrooms.setText("Bathrooms   : "+""+bathroomno);
        kitchens.setText("Kitchens   : "+""+kitchenno);
        balconys.setText("Balconys   : "+""+balconyno);

    }

    public void onOrderClicked(View v)
    {

        try {

            Bundle bundle=new Bundle();
            bundle.putString("type","deep cleaning");
            bundle.putString("bedroom", String.valueOf(bhk));
            bundle.putString("bathroom", String.valueOf(bathroomno));
            bundle.putString("kitchen", String.valueOf(kitchenno));
            bundle.putString("hallno", String.valueOf(hallno));
            bundle.putString("balconyno", String.valueOf(balconyno));
            bundle.putString("hometype",hometype);
            bundle.putString("amount", String.valueOf(amount));

            Intent i = new Intent(this,location_schedule.class);
            i.putExtras(bundle);
            startActivity(i);

        }
        catch (Exception e)
        {
            Log.e("Winjer","Error : "+e);

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.halls1 :

                if(hallno > 1) {
                    hallno--;
                    amount=amount-hall_price;
                }
                halls.setText("Halls   : "+""+hallno);

                amount_text.setText("₹ "+amount);
                break;

            case R.id.halls2 :

                hallno++;
                amount=amount+hall_price;

                halls.setText("Halls   : "+""+hallno);
                amount_text.setText("₹ "+amount);
                break;

            case R.id.bathroom1 :

                if(bathroomno > 1)
                {
                    bathroomno--;
                    amount=amount-bathroom_price;
                }
                bathrooms.setText("Bathrooms   : "+""+bathroomno);
                amount_text.setText("₹ "+amount);
                break;

            case R.id.bathroom2 :

                bathroomno++;
                amount=amount+bathroom_price;
                bathrooms.setText("Bathrooms   : "+""+bathroomno);
                amount_text.setText("₹ "+amount);
                break;

            case R.id.kitchen1 :

                if(kitchenno > 1) {
                    kitchenno--;
                    amount=amount-kitchen_price;
                }
                kitchens.setText("Kitchens   : "+""+kitchenno);
                amount_text.setText("₹ "+amount);
                break;

            case R.id.kitchen2 :

                kitchenno++;
                amount=amount+kitchen_price;
                kitchens.setText("Kitchens   : "+""+kitchenno);
                amount_text.setText("₹ "+amount);
                break;

            case R.id.balcony1 :

                if(balconyno > 1) {
                    balconyno--;
                    amount=amount-balcony_price;
                }
                balconys.setText("Balconys   : "+""+balconyno);
                amount_text.setText("₹ "+amount);
                break;

            case R.id.balcony2 :

                balconyno++;
                amount=amount+balcony_price;
                balconys.setText("Balconys   : "+""+balconyno);
                amount_text.setText("₹ "+amount);
                break;

        }
    }
}