package com.viciexperts.fpapps.vicimobmanager.repository.campaign;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.viciexperts.fpapps.vicimobmanager.entity.Campaign;
import com.viciexperts.fpapps.vicimobmanager.mappers.Mapper;
import com.viciexperts.fpapps.vicimobmanager.mappers.campaign.CampaignToContentValuesMapper;
import com.viciexperts.fpapps.vicimobmanager.mappers.campaign.CursorToCampaignMapper;
import com.viciexperts.fpapps.vicimobmanager.repository.Repository;
import com.viciexperts.fpapps.vicimobmanager.repository.Specification;
import com.viciexperts.fpapps.vicimobmanager.repository.SqlSpecification;
import com.viciexperts.fpapps.vicimobmanager.schema.CampaignSchema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CampaignSqlRepository implements Repository<Campaign> {

    private final SQLiteOpenHelper openHelper;

    private final Mapper<Campaign, ContentValues> toContentValuesMapper;
    private final Mapper<Cursor, Campaign> toCampaignMapper;

    public CampaignSqlRepository(SQLiteOpenHelper openHelper) {
        this.openHelper = openHelper;

        this.toContentValuesMapper = new CampaignToContentValuesMapper();
        this.toCampaignMapper = new CursorToCampaignMapper();
    }

    @Override
    public void add(Campaign item) {
        add(Collections.singletonList(item));
    }

    @Override
    public void add(Iterable<Campaign> items) {
        final SQLiteDatabase database = openHelper.getWritableDatabase();
        database.beginTransaction();

        try {
            for (Campaign item : items) {
                final ContentValues contentValues = toContentValuesMapper.map(item);

                database.insert(CampaignSchema.TABLE, null, contentValues);
            }

            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
            database.close();
        }
    }

    @Override
    public void update(Campaign item) {
        // TODO to be implemented
        final SQLiteDatabase database = openHelper.getWritableDatabase();
        database.beginTransaction();
        String[] parameters={item.getId().toString()};

        try {
            final ContentValues contentValues = toContentValuesMapper.map(item);
            database.update(CampaignSchema.TABLE, contentValues,CampaignSchema.ID +" = ? ",parameters);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
            database.close();
        }
    }

    @Override
    public Campaign get(Specification specification)
    {
        List<Campaign> list = query(specification);
        if(!list.isEmpty())
            return query(specification).get(this.FIRST);
        return new Campaign.CampaignBuilder().build();
    }

    @Override
    public void remove(Campaign item) {
        // TODO to be implemented
        final SQLiteDatabase database = openHelper.getWritableDatabase();
        database.beginTransaction();
        String[] parameters={item.getId().toString()};

        try {
            database.delete(CampaignSchema.TABLE, CampaignSchema.ID +" = ? ",parameters);
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
    public List<Campaign> query(Specification specification) {
        final SqlSpecification sqlSpecification = (SqlSpecification) specification;

        final SQLiteDatabase database = openHelper.getReadableDatabase();
        final List<Campaign> configs = new ArrayList<>();

        try {
            final Cursor cursor = database.rawQuery(sqlSpecification.toSqlQuery(), new String[]{});

            for (int i = 0, size = cursor.getCount(); i < size; i++) {
                cursor.moveToPosition(i);

                configs.add(toCampaignMapper.map(cursor));
            }

            cursor.close();

            return configs;
        } finally {
            database.close();
        }
    }

}
