package com.viciexperts.fpapps.vicimobmanager.mappers;

import com.viciexperts.fpapps.vicimobmanager.entity.Campaign;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


/**
 * Created by maste on 11/20/2017.
 */

public class StringToJSONObjectMapper implements Mapper<String, JSONObject> {

    public JSONObject map(String response)
    {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject = (JSONObject) new JSONTokener(response).nextValue();
        }catch (JSONException e) {
              e.printStackTrace();
        }

        return jsonObject;
    }
}
