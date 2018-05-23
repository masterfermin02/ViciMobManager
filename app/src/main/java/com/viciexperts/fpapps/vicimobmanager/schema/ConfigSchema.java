package com.viciexperts.fpapps.vicimobmanager.schema;

/**
 * Created by maste on 11/19/2017.
 */

public class ConfigSchema {

    public static final String TABLE = "config";
    public static final String VICIDIAL_URL = "vicidial_url";
    public static final String VICIDIAL_FOLDER = "vicidial_folder";
    public static final String USER = "user";
    public static final String PASSWORD = "password";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `"+TABLE+"` ("+
        "`"+VICIDIAL_URL+"` TEXT,"+
	    "`"+VICIDIAL_FOLDER+"`	TEXT,"+
	    "`"+USER+"`	TEXT,"+
	    "`"+PASSWORD+"`	TEXT"+
    ");";
}
