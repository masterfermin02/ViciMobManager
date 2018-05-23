package com.viciexperts.fpapps.vicimobmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.viciexperts.fpapps.vicimobmanager.helpers.DbHelpers;
import com.viciexperts.fpapps.vicimobmanager.repository.Config.ConfigSqlRepository;

public class Config extends AppCompatActivity {

    private ConfigSqlRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        repository = new ConfigSqlRepository(DbHelpers.getDbConnection(this));
    }
}
