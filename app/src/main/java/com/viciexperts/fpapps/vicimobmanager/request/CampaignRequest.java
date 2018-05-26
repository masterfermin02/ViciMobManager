package com.viciexperts.fpapps.vicimobmanager.request;

import android.util.Log;

import com.viciexperts.fpapps.vicimobmanager.adapter.ListCampaignAdapter;
import com.viciexperts.fpapps.vicimobmanager.entity.Campaign;
import com.viciexperts.fpapps.vicimobmanager.mappers.Mapper;
import com.viciexperts.fpapps.vicimobmanager.mappers.StringToJSONObjectMapper;
import com.viciexperts.fpapps.vicimobmanager.mappers.campaign.JSONObjectToCampaignMapper;
import com.viciexperts.fpapps.vicimobmanager.repository.Repository;
import com.viciexperts.fpapps.vicimobmanager.repository.campaign.specifications.AllCampaignSpecification;
import com.viciexperts.fpapps.vicimobmanager.repository.campaign.specifications.DeleteAllCampaignSpecification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class CampaignRequest implements CampaignResponse {

    protected RetrieveFeedTask retrieveFeedTask;
    protected String apiUrl;
    private final Mapper<String, JSONObject> toJSONObjectMapper;
    private final Mapper<JSONObject, Campaign> toCampaignMapper;
    private Repository repository;
    private ListCampaignAdapter listCampaignAdapter;


    public CampaignRequest(Builder builder)
    {
        this.apiUrl = builder.apiUrl;
        this.retrieveFeedTask = new RetrieveFeedTask(apiUrl, this);
        toJSONObjectMapper = new StringToJSONObjectMapper();
        toCampaignMapper = new JSONObjectToCampaignMapper();
        this.repository = builder.repository;
        this.listCampaignAdapter = builder.listCampaignAdapter;

    }

    public RetrieveFeedTask getRetrieveFeedTask()
    {
        return  retrieveFeedTask;
    }

    public void callBack(String response)
    {
        if(response != null)
        {

           JSONObject jsonObject = toJSONObjectMapper.map(response);

            final List<Campaign> campaigns = new ArrayList<>();
            try{
                JSONArray jsonCampaigns = jsonObject.getJSONArray("campaign");
                repository.remove(new DeleteAllCampaignSpecification());
                for (int i = 0; i < jsonCampaigns.length(); i++) {
                    campaigns.add(toCampaignMapper.map(jsonCampaigns.getJSONObject(i)));
                }
                repository.add(campaigns);
                listCampaignAdapter.setCampaigns(campaigns);
                listCampaignAdapter.notifyDataSetChanged();
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.i("INFO", "response : "+response);
    }

    public static class Builder {

        private static String apiUrl;
        private static Repository repository;
        private static ListCampaignAdapter listCampaignAdapter;

        public  Builder(String apiUrl, Repository repository, ListCampaignAdapter listCampaignAdapter)
        {
            this.apiUrl = apiUrl;
            this.repository = repository;
            this.listCampaignAdapter = listCampaignAdapter;

        }

        public Builder()
        {

        }

        public void setApiUrl(String apiUrl)
        {
            this.apiUrl = apiUrl;
        }

        public void setRepository(Repository repository)
        {
            this.repository = repository;
        }

        public void setListCampaignAdapter(ListCampaignAdapter listCampaignAdapter)
        {
            this.listCampaignAdapter = listCampaignAdapter;

        }

        public CampaignRequest biuld()
        {
            return new CampaignRequest(this);
        }
    }

}
