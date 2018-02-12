package com.app.hance.winjer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

public class realfirst extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String Token;
    boolean thread_running = true;
    private boolean backbutton = false;
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realfirst);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);


        //notification tricks
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //for logo in action bar

        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        // getSupportActionBar().setIcon(R.mipmap.ic_logo_m);

        // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        // fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        startActivity(new Intent(realfirst.this,SettingsActivity.class));
        //    }
        // });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //code strts

        bottomNavigation = findViewById(R.id.navigationer);

        fragmentManager = getSupportFragmentManager();

        fragment = new empty();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mcontent, fragment).commit();

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.show:
                        fragment = new empty();
                        break;
                    case R.id.empty:
                        fragment = new profile();
                        break;
                    case R.id.settings:
                        fragment = new booking();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.mcontent, fragment).commit();
                return true;
            }
        });



    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else if (!backbutton) {
            //toast

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.layout,
                    (ViewGroup) findViewById(R.id.toast_layout_root));

            ImageView image = layout.findViewById(R.id.image);
            image.setImageResource(R.mipmap.winjer_small11);
            TextView text = layout.findViewById(R.id.text);
            text.setText("Touch again to exit");

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM, 0, 100);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

            //original
          //  Toast.makeText(this, "Touch again to exit", Toast.LENGTH_LONG).show();
            backbutton = true;
        } else {
            super.onBackPressed();
            finish();
        }
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                backbutton = false;
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.realfirst, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()){

            case R.id.about:

                startActivity(new Intent(realfirst.this,appinfo.class));
                return true;

            case R.id.settings:
                startActivity(new Intent(realfirst.this,location_schedule.class));
                return true;



        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            startActivity(new Intent(realfirst.this,realfirst.class));

        } else if (id == R.id.nav_login) {

            startActivity(new Intent(realfirst.this,signup.class));

        }else if (id == R.id.nav_settings) {

            startActivity(new Intent(realfirst.this,location_schedule.class));

        }  else if (id == R.id.nav_aboutus) {
            startActivity(new Intent(realfirst.this,appinfo.class));

        } else if (id == R.id.nav_contactus) {
            String url = "http://www.tcs.com";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

        } else if (id == R.id.nav_exit) {

            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   /* public void notification(View v){


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Vibrate
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        boolean sw = prefs.getBoolean("vibrate", false);
        if(sw == true){

            long pattern[] = { 0, 1000, 1000, 1000, 1000 };
            vibrator.vibrate(pattern, -1);
        }
        else
            vibrator.cancel();

        //Ringtone
        String alarms = prefs.getString("ringtone", "default ringtone");
        Uri uri = Uri.parse(alarms);

        //Notification
        NotificationManager notificationManager = (NotificationManager)realfirst.this.getSystemService( this.NOTIFICATION_SERVICE );
        Intent intent = new Intent(this,SettingsActivity.class);
        // use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification n  = new NotificationCompat.Builder(realfirst.this)
                .setContentTitle("Milk Container levels are criticlly low")
                .setContentText("Milk Monitor")
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setSound(uri)
                .setSmallIcon(R.drawable.logopng)
                .build();

        //Notification Call
        notificationManager.notify(0, n);
    }*/
}
