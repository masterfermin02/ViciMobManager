package com.viciexperts.fpapps.vicimobmanager.mappers.config;

import android.database.Cursor;
import android.util.Log;

import com.viciexperts.fpapps.vicimobmanager.entity.Config;
import com.viciexperts.fpapps.vicimobmanager.mappers.Mapper;
import com.viciexperts.fpapps.vicimobmanager.schema.ConfigSchema;


/**
 * Created by maste on 11/20/2017.
 */

public class CursorToConfigMapper implements Mapper<Cursor,Config> {

    public Config map(Cursor cursor)
    {

        return new Config
                .ConfigBuilder(cursor.getString(cursor.getColumnIndex(ConfigSchema.VICIDIAL_URL)),
                cursor.getString(cursor.getColumnIndex(ConfigSchema.VICIDIAL_FOLDER)),
                cursor.getString(cursor.getColumnIndex(ConfigSchema.USER)),
                cursor.getString(cursor.getColumnIndex(ConfigSchema.PASSWORD)))
                .id(cursor.getInt(cursor.getColumnIndex(ConfigSchema.ID)))
                .build();
    }
}
