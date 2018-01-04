package com.app.hance.winjer;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import in.goodiebag.carouselpicker.CarouselPicker;

public class secondpage extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {


    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    Button btnGetLastLocation;

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);


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







    //locationMangr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);



        NumberPicker np = (NumberPicker) findViewById(R.id.picker);
        //Initializing a new string array with elements
        final String[] values = {"20 DEC,MON", "21 DEC,TUE", "22 DEC,WED", "24 DEC,THU", "25 DEC,FRI"};


        np.setDividerPadding(1);
        np.setMinValue(0); //from array first value
        np.setMaxValue(values.length - 1); //to array last value
        np.setDisplayedValues(values);
        np.setWrapSelectorWheel(true);
        //Set a value change listener for NumberPicker

        com.shawnlin.numberpicker.NumberPicker np2 = (com.shawnlin.numberpicker.NumberPicker) findViewById(R.id.picker2);
        //Initializing a new string array with elements


        np2.setMinValue(0); //from array first value
        np2.setMaxValue(values.length - 1); //to array last value
        np2.setDisplayedValues(values);
        np2.setWrapSelectorWheel(false);


        PlaceAutocompleteFragment places = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
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
                    Toast.makeText(secondpage.this,
                            "!mGoogleApiClient.isConnected()", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(secondpage.this,
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
                    Toast.makeText(secondpage.this,
                            "permission granted, :)",
                            Toast.LENGTH_LONG).show();
                    // now app have permission so calling getmylocation again
                    //hello
                             getMyLocation();

                } else {
                    Toast.makeText(secondpage.this,
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
                ActivityCompat.requestPermissions(secondpage.this,
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
                    if (addresses.size() > 0)
                         //Get all details from address
                         //cityName = addresses.get(0).getLocality();
                    Toast.makeText(secondpage.this, "" + addresses.get(0).getLocality()
                                                       + addresses.get(0).getSubLocality()
                            + addresses.get(0).getPostalCode(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                                               e.printStackTrace();
                                            }
                Toast.makeText(secondpage.this, String.valueOf(mLastLocation.getLatitude()) + "\n"
                                + String.valueOf(mLastLocation.getLongitude()),
                        Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(secondpage.this,
                        "mLastLocation == null",
                        Toast.LENGTH_LONG).show();
                }
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













