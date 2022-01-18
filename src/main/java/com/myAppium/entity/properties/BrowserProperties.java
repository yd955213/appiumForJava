package com.myAppium.entity.properties;

import com.myAppium.Utils.commm.ReadProperties;

public class BrowserProperties {
    String url;
    String userName;
    String passWord;

    public BrowserProperties(){
        if (null == url) getProperties();
    }

    @Override
    public String toString() {
        return "BrowserProperties{" +
                "url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    private void getProperties(){
        ReadProperties readProperties = new ReadProperties(PropertiesLocation.appiumProperties);
        url = readProperties.getProperty("web.url");
        userName = readProperties.getProperty("web.username");
        passWord = readProperties.getProperty("web.password");
        readProperties.close();
    }
}
