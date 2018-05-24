package com.viciexperts.fpapps.vicimobmanager.entity;


import java.io.Serializable;

/**
 * Created by maste on 11/18/2017.
 */

public class Config implements Serializable {

    protected int id;
    protected String vicidialUrl;
    protected String vicidialFolder;
    protected String user;
    protected String password;


    private Config(ConfigBuilder builder){
        this.id = builder.id;
        this.vicidialUrl = builder.vicidialUrl;
        this.vicidialFolder = builder.vicidialFolder;
        this.user = builder.user;
        this.password = builder.password;
    }

    public Integer getId() {
        return this.id;
    }

    public String getVicidialUrl() {
        return this.vicidialUrl;
    }



    public String getVicidialFolder() {
        return this.vicidialFolder;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }


    @Override
    public String toString() {
        return "Config{" +
                "id=" + id +
                "vicidialUrl=" + vicidialUrl +
                ", vicidialForlder='" + vicidialFolder + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static class ConfigBuilder {
        private static int id;
        private static String vicidialUrl;
        private static String vicidialFolder;
        private static String user;
        private static String password;

        public ConfigBuilder(String vicidialUrl, String vicidialFolder, String user, String password){
            this.vicidialUrl = vicidialUrl;
            this.vicidialFolder = vicidialFolder;
            this.user = user;
            this.password = password;
        }

        public ConfigBuilder()
        {

        }

        public ConfigBuilder id(int id){
            this.id = id;
            return this;
        }

        public ConfigBuilder vicidialUrl(String vicidialUrl){
            this.vicidialUrl = vicidialUrl;
            return this;
        }

        public ConfigBuilder vicidialFolder(String vicidialFolder){
            this.vicidialFolder = vicidialFolder;
            return this;
        }

        public ConfigBuilder user(String user){
            this.user = user;
            return this;
        }

        public ConfigBuilder password(String password){
            this.password = password;
            return this;
        }


        public Config build(){
            return new Config(this);
        }
    }


}