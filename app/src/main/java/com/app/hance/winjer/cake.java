package com.app.hance.winjer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

public class cake extends AppCompatActivity {

    double discount,amount;
    String url="http://tnpmace.com/testingAPI/Booking/listcakeservices";
    RequestQueue requestQueue;
    private RecyclerView recyclerView,hrecyclerView;
    private cakeadapter adapter;
    private List<cakedata> cakeList;
    // adapter = new cakeadapter(this, alb);
    private ProgressDialog pDialog;
    private Boolean response_recieved=false;

    private void prepareAlbums() {



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake);

        recyclerView = findViewById(R.id.recycler_view);

        cakeList= new ArrayList<>();
        adapter = new cakeadapter(this, cakeList);

        prepareAlbums(); //load album list



        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        Log.e("Reached ","Here");
        // Creating volley request obj
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{

                            Log.e("Response", response.toString());

                            //check failure

                            JSONArray jarray = response.getJSONArray("cakes");


                            JSONObject jsonObject ;


                            for (int i=0;i<jarray.length();i++)
                            {
                                jsonObject = jarray.getJSONObject(i);

                                cakedata cakedata=new cakedata();

                                amount=jsonObject.getDouble("amount");
                                discount=(jsonObject.getDouble("discount")/100)*jsonObject.getDouble("amount");
                                amount=amount-discount;

                                cakedata.add(jsonObject.getString("id"),jsonObject.getString("type"),jsonObject.getString("image"), jsonObject.getString("description"),amount,amount+discount);

                                cakeList.add(cakedata);
                            }

                            //int type = Integer.parseInt(jsonObject.getString("type"));

                            Log.e("\n\n\nResponse", jarray.toString());

                            recyclerView.setAdapter(adapter);
                            hidePDialog();


                        }catch(JSONException e)
                        {  e.printStackTrace();

                            Toast.makeText(cake.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
                            //intent to main

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

        try {

            int mNoOfColumns = splash.Utility.calculateNoOfColumns(getApplicationContext());
            RecyclerView.LayoutManager mmLayoutManager = new GridLayoutManager(cake.this, 2);
            recyclerView.setLayoutManager(mmLayoutManager);
            recyclerView.addItemDecoration(new cake.GridSpacingItemDecoration(2, dpToPx(10), true));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

        }
        catch (Exception e)
        {

            Log.e("Hashim","Caught Exception");

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static class Utility {
        public static int calculateNoOfColumns(Context context) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (dpWidth / 140);
            return noOfColumns;
        }
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

}
