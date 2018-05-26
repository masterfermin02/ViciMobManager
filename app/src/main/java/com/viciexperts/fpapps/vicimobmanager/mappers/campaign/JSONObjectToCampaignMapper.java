package com.viciexperts.fpapps.vicimobmanager.mappers.campaign;

import android.database.Cursor;

import com.viciexperts.fpapps.vicimobmanager.entity.Campaign;
import com.viciexperts.fpapps.vicimobmanager.mappers.Mapper;
import com.viciexperts.fpapps.vicimobmanager.schema.CampaignSchema;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by maste on 11/20/2017.
 */

public class JSONObjectToCampaignMapper implements Mapper<JSONObject,Campaign> {

    public Campaign map(JSONObject object)
    {
        Campaign.CampaignBuilder campaign = new Campaign.CampaignBuilder();
        try{
            campaign.setName(object.getString("campaign_id"))
                    .setDescription(object.getString("campaign_name"));
        }catch (JSONException e) {
              e.printStackTrace();
        }

        return campaign.build();
    }
}
