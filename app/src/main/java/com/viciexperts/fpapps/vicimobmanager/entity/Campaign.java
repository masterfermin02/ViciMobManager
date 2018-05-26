package com.viciexperts.fpapps.vicimobmanager.entity;


import java.io.Serializable;

/**
 * Created by maste on 11/18/2017.
 */

public class Campaign implements Serializable {

    protected int id;
    protected String name;
    protected String description;
    protected String active;


    private Campaign(CampaignBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.active = builder.active;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }



    public String getDescription() {
        return this.description;
    }

    public String getActive() {
        return this.active;
    }


    @Override
    public String toString() {
        return "Config{" +
                "id=" + id +
                "name=" + name +
                ", description='" + description + '\'' +
                ", active='" + active + '\'' +
                '}';
    }

    public static class CampaignBuilder {
        private static int id;
        private static String name;
        private static String description;
        private static String active;

        public CampaignBuilder(String name, String description, String active){
            this.name = name;
            this.description = description;
            this.active = active;
        }

        public CampaignBuilder()
        {

        }

        public CampaignBuilder setId(int id){
            this.id = id;
            return this;
        }

        public CampaignBuilder setName(String name){
            this.name = name;
            return this;
        }

        public CampaignBuilder setDescription(String description){
            this.description = description;
            return this;
        }

        public CampaignBuilder setActive(String user){
            this.active = active;
            return this;
        }


        public Campaign build(){
            return new Campaign(this);
        }
    }


}