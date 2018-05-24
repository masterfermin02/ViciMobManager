package com.viciexperts.fpapps.vicimobmanager.helpers;

import android.content.Context;


import com.viciexperts.fpapps.vicimobmanager.dao.ConexionSQLiteHelper;
import com.viciexperts.fpapps.vicimobmanager.entity.Config;
import com.viciexperts.fpapps.vicimobmanager.schema.ConfigSchema;

import java.util.Arrays;
import java.util.List;

/**
 * Created by maste on 11/19/2017.
 */

public class DbHelpers {

    public static final List<String> APP_TABLES_NAMES = Arrays.asList(ConfigSchema.TABLE);
    public static final List<String> APP_CREATE_TABLES = Arrays.asList(ConfigSchema.CREATE_TABLE);
    private static ConexionSQLiteHelper DB_INSTANCE;

    public static final ConexionSQLiteHelper getDbConnection(Context context)
    {
        if(DB_INSTANCE == null){
            DB_INSTANCE = new ConexionSQLiteHelper(context,"vici_mob_manager_db", null, 4);
        }
        return DB_INSTANCE;
    }

}
