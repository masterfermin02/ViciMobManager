package com.viciexperts.fpapps.vicimobmanager.repository.campaign.specifications;


import com.viciexperts.fpapps.vicimobmanager.repository.SqlSpecification;
import com.viciexperts.fpapps.vicimobmanager.schema.CampaignSchema;

/**
 * Created by maste on 11/21/2017.
 */
public class DeleteAllCampaignSpecification implements SqlSpecification {

    @Override
    public String toSqlQuery()
    {
        return String.format(
            "DELETE FROM %1$s ;",
            CampaignSchema.TABLE);
    }
}
