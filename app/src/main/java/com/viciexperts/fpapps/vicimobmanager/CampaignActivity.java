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
import com.viciexperts.fpapps.vicimobmanager.helpers.DbHelpers;
import com.viciexperts.fpapps.vicimobmanager.repository.campaign.CampaignSqlRepository;
import com.viciexperts.fpapps.vicimobmanager.repository.campaign.specifications.AllCampaignSpecification;

import java.util.ArrayList;
import java.util.List;

public class CampaignActivity extends AppCompatActivity {

    private CampaignSqlRepository repository;
    private ListView listView;
    private ListCampaignAdapter listCampaignAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);

        listView = (ListView) findViewById(R.id.listViewCamapigns);

        repository = new CampaignSqlRepository(DbHelpers.getDbConnection(this));

        List<Campaign> listViewCampaigns =  new ArrayList<>(); // repository.query(new AllCampaignSpecification());
        listViewCampaigns.add(new Campaign.CampaignBuilder().setName("campaign 1").build());
        listViewCampaigns.add(new Campaign.CampaignBuilder().setName("campaign 2").build());
        listViewCampaigns.add(new Campaign.CampaignBuilder().setName("campaign 3").build());
        listViewCampaigns.add(new Campaign.CampaignBuilder().setName("campaign 4").build());
        listCampaignAdapter = new ListCampaignAdapter(listViewCampaigns, this);
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
