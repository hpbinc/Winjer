package com.app.hance.winjer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

public class photography extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography);

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        // get the listview
        expListView = findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Birthday Party");
        listDataHeader.add("Wedding Function");
        listDataHeader.add("Corporate Event");
        listDataHeader.add("Other Personal Events");

        // Adding child data
        List<String> birthday = new ArrayList<String>();
        birthday.add("₹25000 - 28 leaf album & Full HD Video");

        List<String> wedding = new ArrayList<String>();
        wedding.add("Bronze - ₹50000 - 45 leaf album & Full HD Video");
        wedding.add("Silver - ₹75000 - 70 leaf album & Full HD Video + Out Door Photos");
        wedding.add("Gold - ₹110000 - 60 leaf album both sides + Full HD Video + Out Door Photos");


        List<String> corporate = new ArrayList<String>();
        corporate.add("Nothing to show");

        List<String> other = new ArrayList<String>();
        other.add("Nothing to show");


        listDataChild.put(listDataHeader.get(0), birthday); // Header, Child data
        listDataChild.put(listDataHeader.get(1), wedding);
        listDataChild.put(listDataHeader.get(2), corporate);
        listDataChild.put(listDataHeader.get(3), other);
    }

    public void bookservice(View v)
    {
        Intent i = new Intent(this,location_schedule.class);
        startActivity(i);
    }


}
