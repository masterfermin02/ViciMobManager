package com.viciexperts.fpapps.vicimobmanager.contracts;

import com.viciexperts.fpapps.vicimobmanager.entity.Campaign;

import java.util.List;

public interface CampaignContract {

    /** Represents the View in MVP. */
    interface View {
        void showCampaigns(List<Campaign> campaigns);
        void editCampaign();
    }

    /** Represents the Presenter in MVP. */
    interface Presenter {
        void loadCampaigns();
    }
}
