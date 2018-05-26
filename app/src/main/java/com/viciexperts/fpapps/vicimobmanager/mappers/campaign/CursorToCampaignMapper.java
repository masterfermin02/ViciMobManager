package com.viciexperts.fpapps.vicimobmanager.mappers.campaign;

import android.database.Cursor;
import android.util.Log;

import com.viciexperts.fpapps.vicimobmanager.entity.Campaign;
import com.viciexperts.fpapps.vicimobmanager.mappers.Mapper;
import com.viciexperts.fpapps.vicimobmanager.schema.CampaignSchema;


/**
 * Created by maste on 11/20/2017.
 */

public class CursorToCampaignMapper implements Mapper<Cursor,Campaign> {

    public Campaign map(Cursor cursor)
    {
        return new Campaign
                .CampaignBuilder(cursor.getString(cursor.getColumnIndex(CampaignSchema.NAME)),
                cursor.getString(cursor.getColumnIndex(CampaignSchema.DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(CampaignSchema.ACTIVE)))
                .setId(cursor.getInt(cursor.getColumnIndex(CampaignSchema.ID)))
                .build();
    }
}
