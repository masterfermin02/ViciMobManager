package com.viciexperts.fpapps.vicimobmanager.dao;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.viciexperts.fpapps.vicimobmanager.helpers.DbHelpers;


/**
 * Created by maste on 11/18/2017.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name , factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        for (String create_table : DbHelpers.APP_CREATE_TABLES) {

            db.execSQL(create_table);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        for (String create_table : DbHelpers.APP_TABLES_NAMES) {
            db.execSQL("DROP TABLE IF EXISTS "+create_table);
        }
        onCreate(db);
    }





}
