package com.viciexperts.fpapps.vicimobmanager.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.viciexperts.fpapps.vicimobmanager.R;
import com.viciexperts.fpapps.vicimobmanager.entity.Campaign;

import java.util.List;

/**
 * Created by maste on 12/4/2017.
 */

public class ListCampaignAdapter extends BaseAdapter {

    private List<Campaign> campaigns;
    private Activity context;

    public ListCampaignAdapter(List<Campaign> campaigns, Activity context){
        this.campaigns = campaigns;
        this.context = context;

    }

    public void setCampaigns(List<Campaign> categories){
        this.campaigns = campaigns;
    }

    @Override
    public int getCount() {
        return campaigns.size();
    }

    @Override
    public Object getItem(int i) {
        return campaigns.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.list_item_campaign_row, null,true);
        }

        Campaign campaign = campaigns.get(i);

        TextView name = (TextView) view.findViewById(R.id.item_campaign_name);
        name.setText(campaign.getName() + ' ' + campaign.getDescription());
        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
