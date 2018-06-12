package com.viciexperts.fpapps.vicimobmanager;


import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
    private Resources res;
    private String saved;
    private String updated;
    private String msj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        res = getResources();
        saved = res.getString(R.string.save);
        updated = res.getString(R.string.update);
        repository = new ConfigSqlRepository(DbHelpers.getDbConnection(this));
        config = repository.get(new ConfigByIdSpecification(1));
        vicidialUrl = (EditText) findViewById(R.id.vicidial_url);
        vicidialFolder = (EditText) findViewById(R.id.vicidial_folder);
        user = (EditText) findViewById(R.id.config_user);
        password = (EditText) findViewById(R.id.config_password);

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
       Config config = new Config.ConfigBuilder(
              vicidialUrl.getText().toString(),
              vicidialFolder.getText().toString(),
              user.getText().toString(),
              password.getText().toString()
        ).id(this.config.getId()).build();

        if(config.getId() != 0) {
            repository.update(config);
            msj = updated;
        }else {
            repository.add(config);
            msj = saved;
        }

        Toast.makeText(this, msj, Toast.LENGTH_SHORT);


    }

    public void clean(View v)
    {
        this.finish();
    }
}
