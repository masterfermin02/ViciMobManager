package com.viciexperts.fpapps.vicimobmanager.mappers;

import java.text.ParseException;

/**
 * Created by maste on 11/20/2017.
 */

public interface Mapper<From, To> {
    To map(From from);
}
