package com.viciexperts.fpapps.vicimobmanager.repository.campaign.specifications;


import com.viciexperts.fpapps.vicimobmanager.repository.SqlSpecification;
import com.viciexperts.fpapps.vicimobmanager.schema.CampaignSchema;

/**
 * Created by maste on 11/20/2017.
 */

public class CampaignByIdSpecification implements SqlSpecification {

    private final int id;

    public CampaignByIdSpecification(final int id) {
        this.id = id;
    }

    @Override
    public String toSqlQuery() {
        return String.format(
                "SELECT * FROM %1$s WHERE `%2$s` = %3$d;",
                CampaignSchema.TABLE,
                CampaignSchema.ID,
                id
        );
    }
}
