package com.viciexperts.fpapps.vicimobmanager.repository.campaign.specifications;


import com.viciexperts.fpapps.vicimobmanager.repository.SqlSpecification;
import com.viciexperts.fpapps.vicimobmanager.schema.CampaignSchema;

/**
 * Created by maste on 11/30/2017.
 */

public class AllCampaignSpecification implements SqlSpecification {

    @Override
    public String toSqlQuery() {
        return String.format(
                "SELECT * FROM %1$s ",
                CampaignSchema.TABLE
        );
    }
}
