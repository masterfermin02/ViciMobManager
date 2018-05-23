package com.viciexperts.fpapps.vicimobmanager.helpers;

import android.content.Context;


import com.viciexperts.fpapps.vicimobmanager.dao.ConexionSQLiteHelper;

import java.util.Arrays;
import java.util.List;

/**
 * Created by maste on 11/19/2017.
 */

public class DbHelpers {

    public static final List<String> APP_TABLES_NAMES = Arrays.asList();
    public static final List<String> APP_CREATE_TABLES = Arrays.asList();
    private static ConexionSQLiteHelper DB_INSTANCE;

    public static final ConexionSQLiteHelper getDbConnection(Context context)
    {
        if(DB_INSTANCE == null){
            DB_INSTANCE = new ConexionSQLiteHelper(context,"vici_mob_manager_db", null, 2);
        }
        return DB_INSTANCE;
    }

}
