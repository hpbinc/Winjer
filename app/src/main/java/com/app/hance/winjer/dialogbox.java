package com.app.hance.winjer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewAnimator;

public class dialogbox extends AppCompatActivity {

    TextView title,description;
    String cakename,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogbox);

        title = findViewById(R.id.cakename);
        description = findViewById(R.id.description);

        Bundle bundle = getIntent().getExtras();
        cakename = bundle.getString("title");
        desc = bundle.getString("description");

        title.setText(cakename);
        description.setText(desc);
    }

    public void ordercake(View v){

        Intent i = new Intent(this,location_schedule.class);
        i.putExtra("type","cake");
        i.putExtra("title",cakename);
        i.putExtra("description",desc);
        startActivity(i);
    }
}
