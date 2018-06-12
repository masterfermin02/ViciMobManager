package com.viciexperts.fpapps.vicimobmanager.presentes;

import com.viciexperts.fpapps.vicimobmanager.contracts.CampaignContract;
import com.viciexperts.fpapps.vicimobmanager.entity.Campaign;

import java.util.List;

public class CampaignPresenter implements CampaignContract.Presenter {

    private List<Campaign> campaigns;
    private CampaignContract.View view;

    public CampaignPresenter(List<Campaign> campaigns, CampaignContract.View view)
    {
        this.campaigns = campaigns;
        this.view = view;

    }

    @Override
    public void loadCampaigns()
    {
        view.showCampaigns(campaigns);

    }


}
