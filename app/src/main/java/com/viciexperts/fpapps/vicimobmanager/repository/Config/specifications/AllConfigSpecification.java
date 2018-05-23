package com.viciexperts.fpapps.vicimobmanager.repository.Config.specifications;


import com.viciexperts.fpapps.vicimobmanager.repository.SqlSpecification;
import com.viciexperts.fpapps.vicimobmanager.schema.ConfigSchema;

/**
 * Created by maste on 11/30/2017.
 */

public class AllConfigSpecification implements SqlSpecification {

    @Override
    public String toSqlQuery() {
        return String.format(
                "SELECT * FROM %1$s ",
                ConfigSchema.TABLE
        );
    }
}
