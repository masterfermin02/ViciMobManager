package com.viciexperts.fpapps.vicimobmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onCampaignsClick(View view)
    {
        // Do something in response to button
        Intent intent = new Intent(this, Campaigns.class);
        startActivity(intent);


    }

    public void onConfigClick(View view)
    {
        // Do something in response to button
        Intent intent = new Intent(this, ConfigActivity.class);
        startActivity(intent);


    }


}
