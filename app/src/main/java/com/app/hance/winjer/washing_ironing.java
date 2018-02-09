package com.app.hance.winjer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;

public class washing_ironing extends AppCompatActivity {

    private RecyclerView recyclerView,hrecyclerView;
    private washing_ironing_adapter adapter;
    private List<washing_ironing_data> mydata;
    int mNoOfColumns=1;

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


public void updateui()
{

    adapter = new washing_ironing_adapter(this,mydata);

    recyclerView.setAdapter(adapter);


}

    public void bookservice(View v)
    {
        Intent i = new Intent(this,location_schedule.class);
        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washing_ironing);

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);


        recyclerView = findViewById(R.id.recycler_view);
        mydata = new ArrayList<>();
        washing_ironing_data a = new washing_ironing_data("Shirts", 45,0);
        mydata.add(a);
        washing_ironing_data b = new washing_ironing_data("Trousers", 40,0);
        mydata.add(b);
        washing_ironing_data c = new washing_ironing_data("Salwar", 100,0);
        mydata.add(c);
        washing_ironing_data d = new washing_ironing_data("Pants", 65,0);
        mydata.add(d);
        washing_ironing_data e= new washing_ironing_data("Jeans", 85,0);
        mydata.add(e);
        washing_ironing_data f = new washing_ironing_data("Saree", 100,0);
        mydata.add(f);

        adapter = new washing_ironing_adapter(this,mydata);

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
                        mydata.get(position).count--;
                        updateui();

                    }
                });
                Button b1= view.findViewById(R.id.minus1);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(washing_ironing.this, "plus :"+position,
                          //      Toast.LENGTH_SHORT).show();
                        mydata.get(position).count++;
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
}
