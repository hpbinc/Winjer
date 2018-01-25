package com.app.hance.winjer;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class location_schedule extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,View.OnClickListener  {

    public static final String MY_PREFS_NAME = "hpbPrefsFile";


    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;


    private int[] btn_id = {R.id.date1, R.id.date2, R.id.date3, R.id.date4,R.id.date5, R.id.date6, R.id.date7};

    Button date[];

    private Button btn_unfocus;


    Button btnGetLastLocation;

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;


    private void setFocus(Button btn_unfocus, Button btn_focus){
        btn_unfocus.setTextColor(Color.rgb(49, 50, 51));
        btn_unfocus.setBackground(getDrawable(R.drawable.button_transparent));
        btn_focus.setTextColor(Color.BLUE);
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

            case R.id.date3 :
                setFocus(btn_unfocus, date[2]);
                break;

            case R.id.date4 :
                setFocus(btn_unfocus, date[3]);
                break;
            case R.id.date5 :
                setFocus(btn_unfocus, date[4]);
                break;
            case R.id.date6 :
                setFocus(btn_unfocus, date[5]);
                break;
            case R.id.date7 :
                setFocus(btn_unfocus, date[6]);
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_schedule);

        //hashim
        date = new Button[7];
        for(int i = 0; i < date.length; i++){
            date[i] = (Button) findViewById(btn_id[i]);
            //date[i].setBackgroundColor(Color.rgb(207, 207, 207));
            date[i].setOnClickListener(this);
        }


        btn_unfocus = date[0];



        setFocus(btn_unfocus,date[0]);

        btnGetLastLocation = (Button) findViewById(R.id.button);
        btnGetLastLocation.setOnClickListener(btnGetLastLocationOnClickListener);
        //textLastLocation = (TextView) findViewById(R.id.lastlocation);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }



        PlaceAutocompleteFragment places = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        ((EditText)places.getView().findViewById(R.id.place_autocomplete_search_input)).setTextSize(18.0f);
        ((EditText)places.getView().findViewById(R.id.place_autocomplete_search_input)).setHint("Landmark");
        //((EditText)places.getView().findViewById(R.id.place_autocomplete_search_input)).setHintTextColor(Color.GRAY);


        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Toast.makeText(getApplicationContext(), place.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {

                Toast.makeText(getApplicationContext(), status.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    /*----------Method to create an AlertBox -------------
    protected void alertbox(String title, String mymessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your Device's GPS is Disable")
                .setCancelable(false)
                .setTitle("** Gps Status **")
                .setPositiveButton("Gps On",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // finish the current activity
                                // AlertBoxAdvance.this.finish();
                                Intent myIntent = new Intent(
                                        Settings.ACTION_SECURITY_SETTINGS);
                                startActivity(myIntent);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // cancel the dialog box
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
*/

    View.OnClickListener btnGetLastLocationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(mGoogleApiClient != null){
                if(mGoogleApiClient.isConnected()){
                    getMyLocation();
                }else{
                    Toast.makeText(location_schedule.this,
                            "!mGoogleApiClient.isConnected()", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(location_schedule.this,
                        "mGoogleApiClient == null", Toast.LENGTH_LONG).show();
            }
        }
    };

    //method called after request permission is fired

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(location_schedule.this,
                            "permission granted, :)",
                            Toast.LENGTH_LONG).show();
                    // now app have permission so calling getmylocation again
                    getMyLocation();

                } else {
                    Toast.makeText(location_schedule.this,
                            "permission denied, ...:(",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }



    private void getMyLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.


            //------------------------------------------------------------------------------
            ActivityCompat.requestPermissions(location_schedule.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            return;
        }
            /* code should explicitly check to see if permission is available
               (with 'checkPermission') or explicitly handle a potential 'SecurityException'
             */
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            String cityName = null;

            Geocoder gcd = new Geocoder(getBaseContext(),Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(mLastLocation.getLatitude(), mLastLocation
                        .getLongitude(), 1);
                if (addresses.size() > 0) {  //Get all details from address
                    //cityName = addresses.get(0).getLocality();
                    //Toast.makeText(location_schedule.this, "" + addresses.get(0).getLocality()
                    //      + addresses.get(0).getSubLocality() + addresses.get(0).getPostalCode(), Toast.LENGTH_SHORT).show();


                    EditText locality=(EditText) findViewById(R.id.locality);
                    EditText pin=(EditText)findViewById(R.id.pin);
                    locality.setText(addresses.get(0).getSubLocality()+","+addresses.get(0).getLocality());
                    pin.setText(addresses.get(0).getPostalCode());

                }


            }catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(location_schedule.this, String.valueOf(mLastLocation.getLatitude()) + "\n"
                            + String.valueOf(mLastLocation.getLongitude()),
                    Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(location_schedule.this,
                    "location not available",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void placeorder(View v)
    {

        EditText house = (EditText)findViewById(R.id.house);
        EditText locality = (EditText)findViewById(R.id.locality);
        EditText pin = (EditText)findViewById(R.id.pin);
        EditText mobile = (EditText)findViewById(R.id.mobile);

        String h = house.getText().toString();
        String l = locality.getText().toString();
        String p = pin.getText().toString();
        String m = mobile.getText().toString();

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("house", h);
        editor.putString("locality", l);
        editor.putString("pin", p);
        editor.putString("mobile", m);
        editor.apply();
        Intent i = new Intent(this,place_order.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}











