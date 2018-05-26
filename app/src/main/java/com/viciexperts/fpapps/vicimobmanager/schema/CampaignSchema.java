package com.viciexperts.fpapps.vicimobmanager.schema;

/**
 * Created by maste on 11/19/2017.
 */

public class CampaignSchema {

    public static final String TABLE = "campaigns";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String ACTIVE = "active";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `"+TABLE+"` ("+
        "`"+ID+"` INTEGER PRIMARY KEY AUTOINCREMENT, "+
        "`"+NAME+"` TEXT,"+
	    "`"+DESCRIPTION+"`	TEXT,"+
	    "`"+ACTIVE+"`	TEXT"+
    ");";
}
