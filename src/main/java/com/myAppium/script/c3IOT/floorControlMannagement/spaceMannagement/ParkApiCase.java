package com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement;

import com.alibaba.fastjson.JSONObject;
import com.myAppium.Utils.commm.JsonUtil;
import com.myAppium.driver.OkHttpDriver;
import com.myAppium.entity.properties.TokenProperties;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity.ListParkRequest;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.interfaces.ParkUrl;

import java.io.IOException;
import java.util.Map;

public class ParkApiCase {
    OkHttpDriver okHttpDriver;
    String token;

    public ParkApiCase(){
        okHttpDriver = OkHttpDriver.getInstance();
        token = new TokenProperties().getToken();
        okHttpDriver.addHeader("token", token);
    }
    public String listPack(String paramJson){

        Map<String, Object> map = (Map) JSONObject.parseObject(paramJson);
        try {
            return okHttpDriver.get(ParkUrl.listPack, map);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public String listPack(ListParkRequest listParkRequest){
        return listPack(JsonUtil.toJsonStringNotNull(listParkRequest, ListParkRequest.class));
    }
}
