package com.myAppium.driver;

import com.myAppium.Utils.commm.JsonUtil;
import com.myAppium.script.c3IOT.login.api.Entity.TokenEntity;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Disabled
class OkHttpDriverTest {
    String jsonString;
//    String ip = "http://172.168.120.230:19088/DevApi/";
    String ip = "http://172.20.1.185:8090/DevApi/";
    OkHttpDriver okHttpDriver;
    String jsonHeader;
    @BeforeEach
    void setUp() {
        jsonHeader = "{\"Content-Type\":\"application/json;charset=utf-8\", \"Connection\":\"keep-alive\"}";
        jsonString = "{\"DeviceUniqueCode\":\"6D19A9\",\"TimeStamp\":\"2020-04-07 09:48:03\",\"Data\":\"null\"}";
        okHttpDriver = OkHttpDriver.getInstance();
    }

    @Test
    void post() {
        okHttpDriver.addHeader(jsonHeader);
        okHttpDriver.postJSon(ip+ "GetDeviceParams", jsonString);
    }
    @Test
    void post1() {
//        okHttpDriver.addHeader(jsonHeader);
        String s = okHttpDriver.postJSon(ip + "GetDeviceParams", jsonString);
        System.out.println("收到的消息" + s);
    }
    @Test
    void run() throws Exception {
        okHttpDriver.run();
    }


    @Test
    @Disabled
    void test(){
        // http://172.168.91.51/auth/api/v1.0/client/token
        // http://172.168.91.51/auth/api/token/verify
        try {
            String s = okHttpDriver.get("http://172.168.91.51/auth/api/token/verify");
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("获取token")
    void getToken(){
        String url = "http://172.168.91.51/auth/api/v1.0/client/token";
//        String value = "eyJVaWQiOiIyODM2OTA5MzA5NjAyMTQwNDkiLCJFeHAiOjE2NDIyMTI3NDIsIlR5cGUiOiJjbGllbnQiLCJJc3MiOiJtcXR0YWNsXzUxIn0=.WeM46y/wlY9XmWqLyud7csjhIn7UQ9Go8Dl3yE2sZlw=";
//        okHttpDriver.addHeader("token", value);
        okHttpDriver.addHeader("SecretKeyId", "client-001");
        okHttpDriver.addHeader("transID", UUID.randomUUID().toString());
        okHttpDriver.addHeader("EncryptType", "none");
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.init();
        String jsonString = new JsonUtil().toJsonStringWithNull(tokenEntity, TokenEntity.class);
        okHttpDriver.potRequestPayLoad(url, jsonString);
    }
    @Test
    void potRequestPayLoad() {
        String url = "http://172.168.91.51/auth/api/token/verify";
        String value = "eyJVaWQiOiIyODM2OTA5MzA5NjAyMTQwNDkiLCJFeHAiOjE2NDIyMTI3NDIsIlR5cGUiOiJjbGllbnQiLCJJc3MiOiJtcXR0YWNsXzUxIn0=.WeM46y/wlY9XmWqLyud7csjhIn7UQ9Go8Dl3yE2sZlw=";
        Map<String, String> map = new HashedMap<>();
        map.put("token", value);
        okHttpDriver.addHeader(jsonHeader);
        okHttpDriver.potRequestPayLoad(url, "{\"token\": \""+value+"\"}");
    }

    @Test
    void listPark(){
        String url = "http://172.168.91.51/admin/lk/api/admin/lkFloor/list";

//        Map map = tokenEntity.toMap(tokenEntity);

    }

    @Test
    void uuid(){
        String s = UUID.randomUUID().toString();
        System.out.println(s);
        System.out.println("51adc6d1-0e0f-4b37-924b-1b3d48e401ba");
    }

    @Test
    void test03(){
        String key = "-----BEGIN RSA PUBLIC KEY-----\nMIIBCgKCAQEAzvH+nLT5IPAQASM5AVWW89WAVP6xUvVy3C7m18KSo3Gi2l+jvly5bIP1B7wy\nmCpp/XB+yDf1juIBakjeepvlSgXj2dSniP1QlWwtXv/4JAvX73ANh+OTavffnoKYPmzex/Hn\nzAaiMlwCxs4ATUUP84fvevaWWsonDAqm1fCu5YABKEBSc8AlxuBE8EzQLfr45lFlZE9LpPji\nTW2Nq1TlQHEUmc+DTqP0bD1DPiiKPD7HNVgun1xE4MrQBWpT57rsFrTN0jbzESF0AsfDdso/\nakBqX1vdUhLK+kBNYUqY//k0QVsmbOpPoOkBPwIPt8iyBT2UxG9jcIkPOk9q0NiRlQIDAQAB\n-----END RSA PUBLIC KEY-----\n";
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.init();
        TokenEntity.Token token = tokenEntity.getToken();
        System.out.println(key.length());
        System.out.println(tokenEntity.getToken().getSecretKeys().get(0).getSecretKey().getProperties().getKey().length());
        System.out.println(key.equals(tokenEntity.getToken().getSecretKeys().get(0).getSecretKey().getProperties().getKey()));
    }
}