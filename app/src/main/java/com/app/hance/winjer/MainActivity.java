package com.app.hance.winjer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridView gridView;

    private String[] titles = {"House Cleaning",
            "Apartment Cleaning",
            "Office Cleaning",
            "Commercial Cleaning",
            "Sofa Cleaning",
            "Carpet Cleaning",
            "House Keeping Service"};

    private int[] images = { R.drawable.winjer_png,
            R.drawable.winjer_png,
            R.drawable.winjer_png,
            R.drawable.winjer_png,
            R.drawable.winjer_png,
            R.drawable.winjer_png,
            R.drawable.winjer_png};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gridView = (GridView)findViewById(R.id.gridview);
        GridAdapter adapter =  new GridAdapter(MainActivity.this,images,titles);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


                        Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();

            }
        });

    }

}
