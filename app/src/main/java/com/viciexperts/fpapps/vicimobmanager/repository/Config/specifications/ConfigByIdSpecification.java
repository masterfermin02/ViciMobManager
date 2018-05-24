package com.viciexperts.fpapps.vicimobmanager.repository.Config.specifications;


import com.viciexperts.fpapps.vicimobmanager.repository.SqlSpecification;
import com.viciexperts.fpapps.vicimobmanager.schema.ConfigSchema;

/**
 * Created by maste on 11/20/2017.
 */

public class ConfigByIdSpecification implements SqlSpecification {

    private final int id;

    public ConfigByIdSpecification(final int id) {
        this.id = id;
    }

    @Override
    public String toSqlQuery() {
        return String.format(
                "SELECT * FROM %1$s WHERE `%2$s` = %3$d;",
                ConfigSchema.TABLE,
                ConfigSchema.ID,
                id
        );
    }
}
