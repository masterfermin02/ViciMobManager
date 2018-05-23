package com.viciexperts.fpapps.vicimobmanager.mappers.config;

import android.content.ContentValues;

import com.viciexperts.fpapps.vicimobmanager.entity.Config;
import com.viciexperts.fpapps.vicimobmanager.mappers.Mapper;
import com.viciexperts.fpapps.vicimobmanager.schema.ConfigSchema;


/**
 * Created by maste on 11/20/2017.
 */

public class ConfigToContentValuesMapper implements Mapper<Config,ContentValues> {

    public ContentValues map(Config config)
    {
        ContentValues values = new ContentValues();
        values.put(ConfigSchema.VICIDIAL_URL, config.getVicidialUrl());;
        values.put(ConfigSchema.VICIDIAL_FOLDER, config.getVicidialFolder());;
        values.put(ConfigSchema.USER, config.getUser());;
        values.put(ConfigSchema.PASSWORD, config.getPassword());;
        return values;
    }
}
