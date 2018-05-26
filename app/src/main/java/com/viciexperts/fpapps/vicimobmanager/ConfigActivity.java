package com.viciexperts.fpapps.vicimobmanager;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.viciexperts.fpapps.vicimobmanager.entity.Config;
import com.viciexperts.fpapps.vicimobmanager.helpers.DbHelpers;
import com.viciexperts.fpapps.vicimobmanager.repository.Config.ConfigSqlRepository;
import com.viciexperts.fpapps.vicimobmanager.repository.Config.specifications.ConfigByIdSpecification;


public class ConfigActivity extends AppCompatActivity {

    private ConfigSqlRepository repository;
    private Config config;
    private EditText vicidialUrl;
    private EditText vicidialFolder;
    private EditText user;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        repository = new ConfigSqlRepository(DbHelpers.getDbConnection(this));
        config = repository.get(new ConfigByIdSpecification(1));
        vicidialUrl = (EditText) findViewById(R.id.vicidial_url);
        vicidialFolder = (EditText) findViewById(R.id.vicidial_folder);
        user = (EditText) findViewById(R.id.config_user);
        password = (EditText) findViewById(R.id.config_password);
        Log.i("guar usuario",config.toString());

        if(config.getVicidialUrl() != null)
        {
            vicidialUrl.setText(config.getVicidialUrl().toString());
            vicidialFolder.setText(config.getVicidialFolder().toString());
            user.setText(config.getUser().toString());
            password.setText(config.getPassword().toString());
        }



    }

    public void save(View v)
    {
       Log.i("guar usuario","testuing...");
       Config config = new Config.ConfigBuilder(
              vicidialUrl.getText().toString(),
              vicidialFolder.getText().toString(),
              user.getText().toString(),
              password.getText().toString()
        ).id(this.config.getId()).build();

        if(config.getId() != 0) {
            Log.i("guar usuario",config.toString());
            repository.update(config);
        }else {
            Log.i("guar usuario",config.toString());
            repository.add(config);
        }


    }

    public void clean(View v)
    {
        this.finish();
    }
}
