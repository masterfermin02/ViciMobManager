package com.viciexperts.fpapps.vicimobmanager.repository.Config;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.viciexperts.fpapps.vicimobmanager.entity.Config;
import com.viciexperts.fpapps.vicimobmanager.mappers.Mapper;
import com.viciexperts.fpapps.vicimobmanager.mappers.config.ConfigToContentValuesMapper;
import com.viciexperts.fpapps.vicimobmanager.mappers.config.CursorToConfigMapper;
import com.viciexperts.fpapps.vicimobmanager.repository.Repository;
import com.viciexperts.fpapps.vicimobmanager.repository.Specification;
import com.viciexperts.fpapps.vicimobmanager.repository.SqlSpecification;
import com.viciexperts.fpapps.vicimobmanager.schema.ConfigSchema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConfigSqlRepository implements Repository<Config> {

    private final SQLiteOpenHelper openHelper;

    private final Mapper<Config, ContentValues> toContentValuesMapper;
    private final Mapper<Cursor, Config> toConfigMapper;

    public ConfigSqlRepository(SQLiteOpenHelper openHelper) {
        this.openHelper = openHelper;

        this.toContentValuesMapper = new ConfigToContentValuesMapper();
        this.toConfigMapper = new CursorToConfigMapper();
    }

    @Override
    public void add(Config item) {
        add(Collections.singletonList(item));
    }

    @Override
    public void add(Iterable<Config> items) {
        final SQLiteDatabase database = openHelper.getWritableDatabase();
        database.beginTransaction();

        try {
            for (Config item : items) {
                final ContentValues contentValues = toContentValuesMapper.map(item);

                database.insert(ConfigSchema.TABLE, null, contentValues);
            }

            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
            database.close();
        }
    }

    @Override
    public void update(Config item) {
        // TODO to be implemented
        final SQLiteDatabase database = openHelper.getWritableDatabase();
        database.beginTransaction();
        String[] parameters={item.getVicidialUrl(), item.getVicidialFolder(), item.getUser(), item.getPassword()};

        try {
            final ContentValues contentValues = toContentValuesMapper.map(item);
            database.update(ConfigSchema.TABLE, contentValues,ConfigSchema.VICIDIAL_URL +" = ? "
                    +ConfigSchema.VICIDIAL_FOLDER +" = ?"
                    +ConfigSchema.USER +" = ?"
                    +ConfigSchema.PASSWORD +" = ?",parameters);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
            database.close();
        }
    }

    @Override
    public Config get(Specification specification)
    {
        List<Config> list = query(specification);
        if(!list.isEmpty())
            return query(specification).get(this.FIRST);
        return new Config.ConfigBuilder().build();
    }

    @Override
    public void remove(Config item) {
        // TODO to be implemented
        final SQLiteDatabase database = openHelper.getWritableDatabase();
        database.beginTransaction();
        String[] parameters={item.getVicidialUrl(), item.getVicidialFolder(), item.getUser(), item.getPassword()};

        try {
            database.delete(ConfigSchema.TABLE, ConfigSchema.VICIDIAL_URL +" = ? "
                    +ConfigSchema.VICIDIAL_FOLDER +" = ?"
                    +ConfigSchema.USER +" = ?"
                    +ConfigSchema.PASSWORD +" = ?",parameters);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
            database.close();
        }
    }

    @Override
    public void remove(Specification specification) {
        // TODO to be implemented
        final SqlSpecification sqlSpecification = (SqlSpecification) specification;
        final SQLiteDatabase database = openHelper.getReadableDatabase();

        try {
            database.execSQL(sqlSpecification.toSqlQuery());
        } finally {
            database.close();
        }
    }

    @Override
    public List<Config> query(Specification specification) {
        final SqlSpecification sqlSpecification = (SqlSpecification) specification;

        final SQLiteDatabase database = openHelper.getReadableDatabase();
        final List<Config> configs = new ArrayList<>();

        try {
            final Cursor cursor = database.rawQuery(sqlSpecification.toSqlQuery(), new String[]{});

            for (int i = 0, size = cursor.getCount(); i < size; i++) {
                cursor.moveToPosition(i);

                configs.add(toConfigMapper.map(cursor));
            }

            cursor.close();

            return configs;
        } finally {
            database.close();
        }
    }

}
