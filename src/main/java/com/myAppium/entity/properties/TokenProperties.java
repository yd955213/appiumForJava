package com.myAppium.entity.properties;

import com.myAppium.Utils.commm.ReadProperties;

public class TokenProperties {
    private static String secretKeyID;
    private static String key;
    private static long expire;
    private static String token;

    public TokenProperties(){
        getProperties();
    }
    private void getProperties(){

        ReadProperties readProperties = new ReadProperties(PropertiesLocation.appiumProperties);
        secretKeyID = readProperties.getProperty("web.SecretKeyId");
        key = readProperties.getProperty("web.token.secretKey.properties.key");
        try {
            expire = Long.parseLong(readProperties.getProperty("web.token.expire"), 10);
        }catch (Exception e){
//            e.printStackTrace();
            expire = 0L;
        }
        token = readProperties.getProperty("web.token");
        readProperties.close();
    }

    public String getSecretKeyID() {
        return secretKeyID;
    }

    public String getKey() {
        return key;
    }

    public long getExpire() {
        return expire;
    }

    public String getToken() {
        return token;
    }
}
