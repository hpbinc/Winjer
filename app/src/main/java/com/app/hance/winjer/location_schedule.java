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
import android.text.TextUtils;
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
import java.util.List;
import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;

public class location_schedule extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,View.OnClickListener  {

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    Button date[];
    Bundle bundle;
    String type,hometype,land,gpsx,gpsy,dateday,time;
    String bedroom,kitchen,hallno,balconyno,bathroomno;
    String amount;
    Button btnGetLastLocation;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
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
    private int[] btn_id = {R.id.date1, R.id.date2, R.id.date3, R.id.date4,R.id.date5, R.id.date6, R.id.date7};
    private Button btn_unfocus;

    private void setFocus(Button btn_unfocus, Button btn_focus){

        btn_unfocus.setBackground(getDrawable(R.drawable.button_transparent));
        btn_focus.setTextColor(Color.WHITE);
        btn_focus.setBackground(getDrawable(R.drawable.buttonstyle2));
        this.btn_unfocus = btn_focus;
    }

    @Override
    public void onClick(View v) {
        //setForcus(btn_unfocus, (Button) findViewById(v.getId()));
        //Or use switch
        switch (v.getId()){
            case R.id.date1 :
                setFocus(btn_unfocus, date[0]);
                dateday = date[0].getText().toString();
                break;

            case R.id.date2 :
                setFocus(btn_unfocus, date[1]);
                dateday = date[1].getText().toString();
                break;

            case R.id.date3 :
                setFocus(btn_unfocus, date[2]);
                dateday = date[2].getText().toString();
                break;

            case R.id.date4 :
                setFocus(btn_unfocus, date[3]);
                dateday = date[3].getText().toString();
                break;
            case R.id.date5 :
                setFocus(btn_unfocus, date[4]);
                dateday = date[4].getText().toString();
                break;
            case R.id.date6 :
                setFocus(btn_unfocus, date[5]);
                dateday = date[5].getText().toString();
                break;
            case R.id.date7 :
                setFocus(btn_unfocus, date[6]);
                dateday = date[6].getText().toString();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_schedule);

        bundle = getIntent().getExtras();
        type = bundle.getString("type");

        Toast.makeText(this, ""+type, Toast.LENGTH_SHORT).show();

        if (type.equals("basic cleaning") || type.equals("deep cleaning")) {
            bedroom = bundle.getString("bedroom");
            kitchen = bundle.getString("kitchen");
            hallno = bundle.getString("hallno");
            bathroomno = bundle.getString("bathroomno");
            balconyno = bundle.getString("balconyno");
            hometype = bundle.getString("hometype");
            amount = bundle.getString("amount");

            //Toast.makeText(this, ""+bedroom+kitchen+hallno+bathroomno+balconyno+hometype+amount, Toast.LENGTH_SHORT).show();
        }
        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Roboto.ttf", true);

        //hashim
        date = new Button[7];
        for (int i = 0; i < date.length; i++) {
            date[i] = findViewById(btn_id[i]);
            //date[i].setBackgroundColor(Color.rgb(207, 207, 207));
            date[i].setOnClickListener(this);
        }


        btn_unfocus = date[0];
        dateday = date[0].getText().toString();
        Toast.makeText(this, ""+dateday, Toast.LENGTH_SHORT).show();


        setFocus(btn_unfocus, date[0]);

        btnGetLastLocation = findViewById(R.id.button);
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
        ((EditText) places.getView().findViewById(R.id.place_autocomplete_search_input)).setTextSize(18.0f);
        ((EditText) places.getView().findViewById(R.id.place_autocomplete_search_input)).setHint("Landmark");
        //((EditText)places.getView().findViewById(R.id.place_autocomplete_search_input)).setHintTextColor(Color.GRAY);


        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Toast.makeText(getApplicationContext(), place.getName(), Toast.LENGTH_SHORT).show();
                land = (String) place.getName();
            }

            @Override
            public void onError(Status status) {

                Toast.makeText(getApplicationContext(), status.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

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
                gpsx = String.valueOf(mLastLocation.getLatitude());
                gpsy = String.valueOf(mLastLocation.getLongitude());
                if (addresses.size() > 0) {  //Get all details from address
                    //cityName = addresses.get(0).getLocality();
                    //Toast.makeText(location_schedule.this, "" + addresses.get(0).getLocality()
                    //      + addresses.get(0).getSubLocality() + addresses.get(0).getPostalCode(), Toast.LENGTH_SHORT).show();


                    EditText locality= findViewById(R.id.locality);
                    EditText pin= findViewById(R.id.pin);
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


    public void confirm(View v)
    {
        int hflag=0,lflag=0,pflag=0,mflag=0;

        EditText house = findViewById(R.id.house);
        if( TextUtils.isEmpty(house.getText())) {
            hflag=1;
            house.setError("House name is required!");
        }
        EditText locality = findViewById(R.id.locality);
        EditText pin = findViewById(R.id.pin);
        if( TextUtils.isEmpty(pin.getText())) {
            pflag=1;
            pin.setError("Pin is required!");
        }
        EditText mobile = findViewById(R.id.mobile);
        if( TextUtils.isEmpty(mobile.getText())) {
            mflag=1;
            mobile.setError("Mobile number is required!");
        }

        EditText comments = findViewById(R.id.remarks);
        String h = house.getText().toString();
        String l = locality.getText().toString();
        String p = pin.getText().toString();
        String m = mobile.getText().toString();
        String c = comments.getText().toString();

        if(hflag==0 && lflag==0 && pflag==0 && mflag==0){

            Bundle bundle=new Bundle();

            Intent i = new Intent(this,place_order.class);


            if(type.equals("basic cleaning") || type.equals("deep cleaning")) {

                i.putExtra("type", type);
                i.putExtra("date", dateday);
                i.putExtra("time", "2:00");
                i.putExtra("address", h+l+p);
                i.putExtra("landmark", land);
                i.putExtra("GPS-X", gpsx);
                i.putExtra("GPS-Y", gpsy);
                i.putExtra("mobile", m);
                i.putExtra("comments", c);
                i.putExtra("bedroom", bedroom);
                i.putExtra("bathroom", bathroomno);
                i.putExtra("kitchen", kitchen);
                i.putExtra("hallno", hallno);
                i.putExtra("balconyno", balconyno);
                i.putExtra("hometype", hometype);
                i.putExtra("amount", amount);

                /*bundle.putString("type", type);
                bundle.putString("date", dateday);
                bundle.putString("time", "2:00");
                bundle.putString("address", h+l+p);
                bundle.putString("landmark", land);
                bundle.putString("GPS-X", gpsx);
                bundle.putString("GPS-Y", gpsy);
                bundle.putString("mobile", m);
                bundle.putString("comments", c);
                bundle.putInt("bedroom", bedroom);
                bundle.putInt("bathroom", bathroomno);
                bundle.putInt("kitchen", kitchen);
                bundle.putInt("hallno", hallno);
                bundle.putInt("balconyno", balconyno);
                bundle.putString("hometype", hometype);
                bundle.putFloat("amount", amount);*/
                Log.e("details",""+type+"::"+kitchen+"::"+balconyno);
            }

            startActivity(i);

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











