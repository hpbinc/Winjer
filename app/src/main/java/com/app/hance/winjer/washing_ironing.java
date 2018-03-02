package com.app.hance.winjer;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;

public class washing_ironing extends AppCompatActivity {

    public static data info;
    int mNoOfColumns=1;
    TextView price;
    String url="http://tnpmace.com/testingAPI/Booking/listWashingNIroning";
    RequestQueue requestQueue;
    Boolean response_recieved=false;
    private RecyclerView recyclerView,hrecyclerView;
    private washing_ironing_adapter adapter;
    private List<washing_ironing_data> mydata;

    public void updateui()
    {

        price.setText("₹ "+washing_ironing_data.amount);
        adapter = new washing_ironing_adapter(this,mydata);

        recyclerView.setAdapter(adapter);


    }

    public void bookservice(View v)
    {
        Intent i = new Intent(this,location_schedule.class);
        // i.putExtra("washing_data", (Parcelable) mydata);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washing_ironing);

        price= findViewById(R.id.price);
        price.setText("₹ "+washing_ironing_data.amount);
        mydata = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new washing_ironing_adapter(this,mydata);


        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        //Recieve service infos..
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{

                            info=new data();


                            Log.e("Response", response.toString());

                            JSONArray jarray = response.getJSONArray("services");


                            for(int i=0; i < jarray.length(); i++) {

                                JSONObject jsonObject = jarray.getJSONObject(i);
                                washing_ironing_data a = new washing_ironing_data(jsonObject.getString("item"),Float.parseFloat(jsonObject.getString("price")),0);
                                mydata.add(a);


                            }


                            updateui();
                            response_recieved=true;





                        }catch(JSONException e)
                        {   e.printStackTrace();
                            Toast.makeText(washing_ironing.this, " Network Error Occured ", Toast.LENGTH_SHORT).show();

                        }
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



        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                // Toast.makeText(washing_ironing.this, "Single Click on position :"+position,
                //       Toast.LENGTH_SHORT).show();
                Button b= view.findViewById(R.id.minus);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(washing_ironing.this, "minus :"+position,
                        //       Toast.LENGTH_SHORT).show();
                        if(mydata.get(position).count>0) {
                            mydata.get(position).count--;
                            washing_ironing_data.amount = washing_ironing_data.amount - mydata.get(position).price;
                            updateui();
                        }

                    }
                });
                Button b1= view.findViewById(R.id.minus1);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(washing_ironing.this, "plus :"+position,
                        //      Toast.LENGTH_SHORT).show();
                        mydata.get(position).count++;
                        washing_ironing_data.amount=washing_ironing_data.amount+mydata.get(position).price;
                        updateui();
                    }
                });
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(washing_ironing.this, "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();

                updateui();

            }



        }));


    }

    public interface ClickListener{
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
