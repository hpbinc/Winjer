package com.app.hance.winjer;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class empty extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView ,recyclerView2,recyclerView3;
    private AlbumsAdapter adapter, adapter2, adapter3;
    private List<Album> albumList,albumList2,albumList3;

    private OnFragmentInteractionListener mListener;


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootview = inflater.inflate(R.layout.fragment_empty, container, false);



        recyclerView = rootview.findViewById(R.id.recycler_view);
        recyclerView2 = rootview.findViewById(R.id.recycler_view2);
        recyclerView3 = rootview.findViewById(R.id.recycler_view3);

        albumList = new ArrayList<>();
        albumList2 = new ArrayList<>();
        albumList3 = new ArrayList<>();

        adapter = new AlbumsAdapter(getContext(), albumList , R.layout.album_card);
        adapter2 = new AlbumsAdapter(getContext(), albumList2 , R.layout.singlecard);
        adapter3 = new AlbumsAdapter(getContext(), albumList3 , R.layout.single_card2);

        int mNoOfColumns = splash.Utility.calculateNoOfColumns(getContext());

        prepareAlbums();

        prepareAlbums2();

        prepareAlbums3();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), mNoOfColumns);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(mLayoutManager2);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(adapter2);

        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
         recyclerView3.setLayoutManager(mLayoutManager3);
        //recyclerView3.setLayoutManager(mLayoutManager3);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        recyclerView3.setAdapter(adapter3);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView3.setNestedScrollingEnabled(false);

        return rootview;

    }

    private void prepareAlbums3() {
        int[] covers = new int[]{
                R.drawable.basiccleaning,
                R.drawable.deepcleaning,
                R.drawable.sofa,
                R.drawable.carpet,
                R.drawable.commercial,
                R.drawable.housekeeping};

        Album a = new Album("BASIC", covers[0]);
        albumList3.add(a);

        a = new Album("DEEP", covers[1]);
        albumList3.add(a);

        a = new Album("SOFA", covers[2]);
        albumList3.add(a);

        a = new Album("CARPET",covers[3]);
        albumList3.add(a);

        a = new Album("COMMERCIAL",covers[4]);
        albumList3.add(a);

        a = new Album("HOUSE KEEPING",covers[5]);
        albumList3.add(a);

    }

    private void prepareAlbums2() {
        int[] covers = new int[]{
                R.drawable.offer1,
                R.drawable.offer2,
                R.drawable.offer3};

        Album a = new Album("", covers[0]);
        albumList2.add(a);

        a = new Album("", covers[1]);
        albumList2.add(a);

        a = new Album("", covers[2]);
        albumList2.add(a);

    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.basiccleaning,
                R.drawable.deepcleaning,
                R.drawable.party,
                R.drawable.washing,
                R.drawable.drycleaning,
                R.drawable.acrepair,
                R.drawable.electrical,
                R.drawable.plumbing,
                R.drawable.mobile,
                R.drawable.tvrepair,
                R.drawable.photography,
                R.drawable.event,
                R.drawable.tailoring,
                R.drawable.cake,
                R.drawable.paint,
                R.drawable.blood,
                R.drawable.roofwork};

        Album a = new Album("BASIC CLEANING", covers[0]);
        albumList.add(a);

        a = new Album("DEEP CLEANING", covers[1]);
        albumList.add(a);

        a = new Album("PARTY CLEANING", covers[2]);
        albumList.add(a);

        a = new Album("WASHING AND IRONING",covers[3]);
        albumList.add(a);

        a = new Album("DRY CLEANING",covers[4]);
        albumList.add(a);

        a = new Album("AC SERVICE",covers[5]);
        albumList.add(a);

        a = new Album("ELECTRICAL", covers[6]);
        albumList.add(a);

        a = new Album("PLUMBING", covers[7]);
        albumList.add(a);

        a = new Album("MOBILE REPAIR",covers[8]);
        albumList.add(a);

        a = new Album("TV REPAIR",covers[9]);
        albumList.add(a);

        a = new Album("PHOTOGRAPHY",covers[10]);
        albumList.add(a);

        a = new Album("EVENT MANAGEMENT", covers[11]);
        albumList.add(a);

        a = new Album("TAILORING",covers[12]);
        albumList.add(a);

        a = new Album("CAKE", covers[13]);
        albumList.add(a);

        a = new Album("PAINTING",covers[14]);
        albumList.add(a);

        a = new Album("LAB", covers[15]);
        albumList.add(a);

        a = new Album("ROOF WORK",covers[16]);
        albumList.add(a);

    }




    // TODO: Rename method, update argument and hook method into UI event


}