package com.viciexperts.fpapps.vicimobmanager.mappers.campaign;

import android.content.ContentValues;

import com.viciexperts.fpapps.vicimobmanager.entity.Campaign;
import com.viciexperts.fpapps.vicimobmanager.entity.Config;
import com.viciexperts.fpapps.vicimobmanager.mappers.Mapper;
import com.viciexperts.fpapps.vicimobmanager.schema.CampaignSchema;
import com.viciexperts.fpapps.vicimobmanager.schema.ConfigSchema;


/**
 * Created by maste on 11/20/2017.
 */

public class CampaignToContentValuesMapper implements Mapper<Campaign,ContentValues> {

    public ContentValues map(Campaign campaign)
    {
        ContentValues values = new ContentValues();
        values.put(CampaignSchema.NAME, campaign.getName());
        values.put(CampaignSchema.DESCRIPTION, campaign.getDescription());
        values.put(CampaignSchema.ACTIVE, campaign.getActive());
        return values;
    }
}
