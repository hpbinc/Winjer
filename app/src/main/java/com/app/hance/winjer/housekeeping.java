package com.app.hance.winjer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jaredrummler.materialspinner.MaterialSpinner;

import me.anwarshahriar.calligrapher.Calligrapher;

public class housekeeping extends AppCompatActivity {

    String area,staffs,leaders,supervisors,fmanagers,managers,time1,time2,cname,location,number,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housekeeping);

        //font

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Roboto.ttf",true);

        MaterialSpinner spinner = findViewById(R.id.spinner);
        spinner.setItems("Mall", "Hotel","Office","Showroom", "Garage", "Hospital","Apartment");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();

                area = item;
            }
        });

    }

    public void bookservice(View v)
    {

        EditText staffsE = findViewById(R.id.staffs);
        staffs = staffsE.getText().toString();
        EditText leadersE = findViewById(R.id.staffs);
        leaders = leadersE.getText().toString();
        EditText supervisorsE = findViewById(R.id.staffs);
        supervisors = supervisorsE.getText().toString();
        EditText fmanagersE = findViewById(R.id.staffs);
        fmanagers = fmanagersE.getText().toString();
        EditText managersE = findViewById(R.id.staffs);
        managers = managersE.getText().toString();
        EditText time1E = findViewById(R.id.staffs);
        time1 = time1E.getText().toString();
        EditText time2E = findViewById(R.id.staffs);
        time2 = time2E.getText().toString();
        EditText cnameE = findViewById(R.id.staffs);
        cname = cnameE.getText().toString();
        EditText locationE = findViewById(R.id.staffs);
        location = locationE.getText().toString();
        EditText numberE = findViewById(R.id.staffs);
        number = numberE.getText().toString();
        EditText emailE = findViewById(R.id.staffs);
        email = emailE.getText().toString();

        Intent i = new Intent(this,location_schedule.class);
        i.putExtra("type","housekeeping");
        i.putExtra("staffs",staffs);
        i.putExtra("leaders",leaders);
        i.putExtra("supervisors",supervisors);
        i.putExtra("fmanagers",fmanagers);
        i.putExtra("managers",managers);
        i.putExtra("time1",time1);
        i.putExtra("time2",time2);
        i.putExtra("cname",cname);
        i.putExtra("location",location);
        i.putExtra("number",number);
        i.putExtra("email",email);
        startActivity(i);
    }
}
