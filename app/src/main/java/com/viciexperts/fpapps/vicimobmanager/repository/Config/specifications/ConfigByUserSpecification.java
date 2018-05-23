package com.viciexperts.fpapps.vicimobmanager.repository.Config.specifications;


import com.viciexperts.fpapps.vicimobmanager.repository.SqlSpecification;
import com.viciexperts.fpapps.vicimobmanager.schema.ConfigSchema;

/**
 * Created by maste on 11/20/2017.
 */

public class ConfigByUserSpecification implements SqlSpecification {

    private final String user;

    public ConfigByUserSpecification(final String user) {
        this.user = user;
    }

    @Override
    public String toSqlQuery() {
        return String.format(
                "SELECT * FROM %1$s WHERE `%2$s` = %3$d;",
                ConfigSchema.TABLE,
                ConfigSchema.USER,
                user
        );
    }
}
