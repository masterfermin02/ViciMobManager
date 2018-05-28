package com.viciexperts.fpapps.vicimobmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.viciexperts.fpapps.vicimobmanager.adapter.ListCampaignAdapter;
import com.viciexperts.fpapps.vicimobmanager.entity.Campaign;
import com.viciexperts.fpapps.vicimobmanager.entity.Config;
import com.viciexperts.fpapps.vicimobmanager.helpers.DbHelpers;
import com.viciexperts.fpapps.vicimobmanager.repository.Config.ConfigSqlRepository;
import com.viciexperts.fpapps.vicimobmanager.repository.Config.specifications.ConfigByIdSpecification;
import com.viciexperts.fpapps.vicimobmanager.repository.campaign.CampaignSqlRepository;
import com.viciexperts.fpapps.vicimobmanager.repository.campaign.specifications.AllCampaignSpecification;
import com.viciexperts.fpapps.vicimobmanager.request.*;

import java.util.ArrayList;
import java.util.List;

public class CampaignActivity extends AppCompatActivity {

    private CampaignSqlRepository repository;
    private ListView listView;
    private ListCampaignAdapter listCampaignAdapter;
    private CampaignRequest campaignRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);


        listView = (ListView) findViewById(R.id.listViewCamapigns);

        repository = new CampaignSqlRepository(DbHelpers.getDbConnection(this));


        List<Campaign> listViewCampaigns =  repository.query(new AllCampaignSpecification());
        ConfigSqlRepository configSqlRepository = new ConfigSqlRepository(DbHelpers.getDbConnection(this));
        Config config = configSqlRepository.get(new ConfigByIdSpecification(1));
        listCampaignAdapter = new ListCampaignAdapter(listViewCampaigns, this);
        campaignRequest = new CampaignRequest.Builder("http://"+config.getVicidialUrl()+"/"+config.getVicidialFolder()+"/non_agent_api_beta.php?source=test&user="+config.getUser()+"&pass="+config.getPassword()+"&function=get_campaign&json=1",
                repository,listView,
                this).biuld();
        campaignRequest.getRetrieveFeedTask().execute();
        listView.setAdapter(listCampaignAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Campaign c = (Campaign) adapterView.getItemAtPosition(i);
                /*Intent rCategory = new Intent(CampaignActivity.this, RegisterCategory.class);
                Category c = (Category) adapterView.getItemAtPosition(i);

                rCategory.putExtra("category", c);
                startActivity(rCategory);*/
                String text = "Campaign :  "+c.getName();
                Integer duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(CampaignActivity.this, text, duration);
                toast.show();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        /*listCategoryAdapter.setCategories(repository.query(new AllCategorySpecificacion()));
        listCategoryAdapter.notifyDataSetChanged();*/

    }
}
