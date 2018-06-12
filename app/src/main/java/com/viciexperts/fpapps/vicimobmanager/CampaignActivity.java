package com.viciexperts.fpapps.vicimobmanager;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.viciexperts.fpapps.vicimobmanager.adapter.ListCampaignAdapter;
import com.viciexperts.fpapps.vicimobmanager.contracts.CampaignContract;
import com.viciexperts.fpapps.vicimobmanager.entity.Campaign;
import com.viciexperts.fpapps.vicimobmanager.entity.Config;
import com.viciexperts.fpapps.vicimobmanager.helpers.DbHelpers;
import com.viciexperts.fpapps.vicimobmanager.mappers.Mapper;
import com.viciexperts.fpapps.vicimobmanager.mappers.StringToJSONObjectMapper;
import com.viciexperts.fpapps.vicimobmanager.mappers.campaign.JSONObjectToCampaignMapper;
import com.viciexperts.fpapps.vicimobmanager.presentes.CampaignPresenter;
import com.viciexperts.fpapps.vicimobmanager.repository.Config.ConfigSqlRepository;
import com.viciexperts.fpapps.vicimobmanager.repository.Config.specifications.ConfigByIdSpecification;
import com.viciexperts.fpapps.vicimobmanager.repository.campaign.CampaignSqlRepository;
import com.viciexperts.fpapps.vicimobmanager.repository.campaign.specifications.AllCampaignSpecification;
import com.viciexperts.fpapps.vicimobmanager.repository.campaign.specifications.DeleteAllCampaignSpecification;
import com.viciexperts.fpapps.vicimobmanager.request.*;
import com.viciexperts.fpapps.vicimobmanager.views.campaigns.CampaignFormActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CampaignActivity extends AppCompatActivity implements CampaignContract.View, AdapterView.OnItemClickListener, CampaignRequest.CampaignResponse {

    private CampaignSqlRepository repository;
    private ListView listView;
    private ListCampaignAdapter listCampaignAdapter;
    private CampaignRequest campaignRequest;
    private DbHelpers db;
    private CampaignContract.Presenter presenter;
    private final Mapper<String, JSONObject> toJSONObjectMapper = new StringToJSONObjectMapper();
    private final Mapper<JSONObject, Campaign> toCampaignMapper = new JSONObjectToCampaignMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);
        iniView();
        db = new DbHelpers();
        repository = new CampaignSqlRepository(db.getDbConnection(this));
        ConfigSqlRepository configSqlRepository = new ConfigSqlRepository(db.getDbConnection(this));
        Config config = configSqlRepository.get(new ConfigByIdSpecification(1));
        String url = "http://"+config.getVicidialUrl()+"/"+config.getVicidialFolder()+"/non_agent_api_beta.php?source=test&user="+config.getUser()+"&pass="+config.getPassword()+"&function=get_campaign&json=1";
        campaignRequest = new CampaignRequest.Builder(url,this).biuld();
        campaignRequest.getRetrieveFeedTask().execute();
        List<Campaign> campaigns = new ArrayList<>();
        listCampaignAdapter = new ListCampaignAdapter(campaigns,this);
        listView.setAdapter(listCampaignAdapter);
        presenter = new CampaignPresenter(repository.query(new AllCampaignSpecification()),this);
        presenter.loadCampaigns();


    }

    private void iniView()
    {
        listView = findViewById(R.id.listViewCamapigns);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Campaign c = (Campaign) adapterView.getItemAtPosition(i);
        Intent rCampaignDetail = new Intent(CampaignActivity.this, CampaignFormActivity.class);

        rCampaignDetail.putExtra("campaign", c);
        startActivity(rCampaignDetail);

    }

    @Override
    public void showCampaigns(List<Campaign> campaigns)
    {
        listCampaignAdapter.setCampaigns(campaigns);
    }

    @Override
    public void editCampaign()
    {

    }

    @Override
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
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void onResume() {
        super.onResume();

        /*listCategoryAdapter.setCategories(repository.query(new AllCategorySpecificacion()));
        listCategoryAdapter.notifyDataSetChanged();*/

    }
}
